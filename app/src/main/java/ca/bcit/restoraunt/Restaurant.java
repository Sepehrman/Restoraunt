package ca.bcit.restoraunt;

public class Restaurant {
    private String name;
    private String address;

    public Restaurant(String name, String address){
        this.name = name;
        this.address = address;
    }

    public static final Restaurant[] restaurants = {
            new Restaurant("Mcdonalds", "123 street")
    };

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public static Restaurant[] getAllRestaurants() {
        return restaurants;
    }
}
