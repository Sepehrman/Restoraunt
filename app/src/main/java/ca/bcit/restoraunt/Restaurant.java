package ca.bcit.restoraunt;

import java.io.Serializable;

public class Restaurant implements Serializable {
    private String name;
    private String address;
    private String imgURL;
    private String latLong;

    public Restaurant() {

    }

    public Restaurant(String name, String address, String imgURL, String latLong){
        this.name = name;
        this.address = address;
        this.imgURL = imgURL;
        this.latLong = latLong;
    }


    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getImgURL() { return imgURL; }

    public String getLatLong() { return latLong; }
}
