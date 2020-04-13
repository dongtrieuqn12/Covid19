package vn.cns.covid19.base;

import android.nfc.tech.IsoDep;
import android.util.Log;

import java.io.IOException;

import vn.cns.covid19.Utils.ByteUtils;
import vn.cns.covid19.Utils.Const;

public class NFCReader {
    private static NFCReader mInstance;
    private IsoDep IsoDepCard;

    private NFCReader(IsoDep card) throws IOException {
        IsoDepCard = card;
        IsoDepCard.connect();
        IsoDepCard.setTimeout(500);
    }

    public static NFCReader getInstance (IsoDep isoDepCard) throws IOException {
        synchronized (NFCReader.class) {
            if (mInstance == null) {
                mInstance = new NFCReader(isoDepCard);
            }

            return mInstance;
        }
    }

    private String readData (String fileID, String offSet, int lengthInt) throws IOException {
        Log.d (Const.TAG,"readData--------------------------------------------------------");
        String length = ByteUtils.byteArray2HexString(ByteUtils.int2bytearray(lengthInt,3));
        //                                         01       03       03
        String nativeReadCmd = "90BD" + "0000"+ "07" + fileID + offSet + length + "00";
        Log.d(Const.TAG,nativeReadCmd);
        String s = send2Card(nativeReadCmd);
        if(s == null) {
            return null;
        }
        if (s.endsWith("9100") || s.endsWith("91AF")) {
            Log.d(Const.TAG, "Read action succeed");
            return s.substring(0,s.length ()-4);
        } else {
            Log.d(Const.TAG, "Read action error");
            return null;
        }
    }

    private String readDataFull (String fileID, String offSet, int lengthInt) throws IOException {
        String data = null;
        String length = ByteUtils.byteArray2HexString(ByteUtils.int2bytearray(lengthInt,3));
        String nativeReadCmd = "90AD" + "0000"+ "07" + fileID + offSet + length + "00";
        String s = send2Card(nativeReadCmd);
        if(s == null) {
            return null;
        }
        for (int i = 0; i < 10; i++) {
            if (s != null) {
                if (s.endsWith("9100")) {
                    data += s.substring(0,s.length()-4);
                    break;
                } else if (s.endsWith("91AF")) {
                    data += s.substring(0,s.length()-4);
                    s = send2Card("90AF");
                } else {
                    return null;
                }
            }
        }
        return data;
    }

    private String send2Card(String data) throws IOException {
        byte[] response = IsoDepCard.transceive(ByteUtils.hexString2ByteArray(data));
        return ByteUtils.byteArray2HexString(response);
    }

    private boolean selectApp(String AID) throws IOException {
        String s = send2Card("905A" + "0000" + "03"+ AID + "00");
        if(s == null) {
            return false;
        }
        if (s.endsWith("9100")) {
            return true;
        } else {
            return false;
        }
    }

    public String getCardID() throws IOException {
        selectApp("0100DF");
//        if (!selectApp("0100DF")) {
//            return null;
//        }
        String s = readData("01","040000",10);
        if(s != null) {
            return s.substring(1);
        } else {
            return null;
        }
    }

    public String readNameFull() throws IOException {
        selectApp("0300DF");
        String s = readDataFull("01","060000",60);
        String s2;
        try {
            s2 = new String(ByteUtils.hexString2ByteArray(s.substring(0, 60 * 2)));
        } catch (StringIndexOutOfBoundsException e) {
            return null;
        }
        String[] parts = s2.split("\\*");
        return parts[0];
    }

    public String readOrgCode(boolean isAfterReadname) throws IOException {
        if(!isAfterReadname) {
            selectApp("0300DF");  // Có thể bỏ nếu lệnh này thực hiệu ngay sau lệnh đọc tên
        }
        return readDataFull("01","4E0000",5);
    }

    public String readCustomerID(boolean isAfterReadname) throws IOException {
        if(!isAfterReadname) {
            selectApp("0300DF");  // Có thể bỏ nếu lệnh này thực hiệu ngay sau lệnh đọc tên
        }
        return readDataFull("01","000000",6);
    }
}
