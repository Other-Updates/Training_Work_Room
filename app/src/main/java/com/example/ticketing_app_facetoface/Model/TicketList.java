package com.example.ticketing_app_facetoface.Model;

public class TicketList {
    private String vCategoryName;
    private int imgId;

    public TicketList (String vCategoryName,int imgId) {
        this.vCategoryName = vCategoryName;
        this.imgId = imgId;
    }

    public String getvCategoryName() {
        return vCategoryName;
   }
    public void setvCategoryName(String vCategoryName) { this.vCategoryName = vCategoryName;
    }
    public int getImgId() {
        return imgId;
    }
    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}

