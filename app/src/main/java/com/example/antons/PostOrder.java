package com.example.antons;

import com.google.gson.annotations.SerializedName;

public class PostOrder {

    @SerializedName("datum")
    private String date;

    @SerializedName("tid")
    private String time;
    @SerializedName("kommentar")
    private String comment;
    @SerializedName("bordId")
    private int tableId;

}
