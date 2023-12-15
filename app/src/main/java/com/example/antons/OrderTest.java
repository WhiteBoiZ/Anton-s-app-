package com.example.antons;

import com.google.gson.annotations.SerializedName;

public class OrderTest {

    private static class Table {
        @SerializedName("id")
        private int id;
        @SerializedName("status")
        private String status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "Table{" +
                    "id=" + id +
                    ", status='" + status + '\'' +
                    '}';
        }
    }

    @SerializedName("id")
    private int id;
    @SerializedName("datum")
    private String date;
    @SerializedName("tid")
    private String time;
    @SerializedName("kommentar")
    private String comment;
    @SerializedName("bordId")
    private Table table;

    @SerializedName("forrattKlar")
    private boolean startDone;
    @SerializedName("varmrattKlar")
    private boolean mainDone;

    @SerializedName("efterrattKlar")
    private boolean dessertDone;

    public boolean isStartDone() {
        return startDone;
    }

    public void setStartDone(boolean startDone) {
        this.startDone = startDone;
    }

    public boolean isMainDone() {
        return mainDone;
    }

    public void setMainDone(boolean mainDone) {
        this.mainDone = mainDone;
    }

    public boolean isDessertDone() {
        return dessertDone;
    }

    public void setDessertDone(boolean dessertDone) {
        this.dessertDone = dessertDone;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Table getTable() {
        return table;
    }

    public int getTableID() {return table.getId();}

    public void setTable(Table table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return "OrderTest{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", comment='" + comment + '\'' +
                ", table=" + table +
                ", startDone=" + startDone +
                ", mainDone=" + mainDone +
                ", dessertDone=" + dessertDone +
                '}';
    }


}
