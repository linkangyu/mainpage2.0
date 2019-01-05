package com.example.smilewithu.mainpage;

public class Record {
    private String date;
    private String kind;
    private String money;
    private String message;
    private int imageID;
    public Record()
    {

    }
    public Record(String date, String kind, String money, String message, int imageID)
    {
        this.date = date;
        this.kind = kind;
        this.money = money;
        this.message = message;
        this.imageID=imageID;
    }
    public void setID(int imageID)
    {
        this.imageID=imageID;
    }
    public int getImageId()
    {
        return imageID;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getKind() {
        return kind;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getMoney() {
        return money;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
