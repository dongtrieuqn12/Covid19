package vn.cns.covid19.model.orders;

public class NoteOrder {
    private boolean isDelivered;
    private String dateTime;

    public NoteOrder(boolean isDelivered, String dateTime) {
        this.isDelivered = isDelivered;
        this.dateTime = dateTime;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
