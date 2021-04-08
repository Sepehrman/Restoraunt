package ca.bcit.restoraunt;

public class Restaurant {
    private String name;
    private String address;
    private String imgURL;

    public Restaurant() {

    }

    public Restaurant(String name, String address, String imgURL){
        this.name = name;
        this.address = address;
        this.imgURL = imgURL;
    }


    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getImgURL() { return imgURL; }

}
