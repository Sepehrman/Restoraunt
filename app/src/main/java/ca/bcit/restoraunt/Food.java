package ca.bcit.restoraunt;

public class Food {

    private String foodName;
    private String foodPrice;

    public Food(String foodName, String foodPrice) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
    }


    public String getFoodName() {
        return foodName;
    }

    public String getFoodPrice() {
        return foodPrice;
    }
}
