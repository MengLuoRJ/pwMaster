package DataControl;

public class TCB {
    private int ID;
    private String TABLE_NAME;

    public TCB(int ID, String TABLE_NAME) {
        this.ID = ID;
        this.TABLE_NAME = TABLE_NAME;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTABLE_NAME() {
        return TABLE_NAME;
    }

    public void setTABLE_NAME(String TABLE_NAME) {
        this.TABLE_NAME = TABLE_NAME;
    }
}
