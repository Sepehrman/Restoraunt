package ca.bcit.restoraunt;

public class Food {

    private String foodName;
    private String foodPrice;

    public Food(String foodName, String foodPrice) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
    }

    public Food(){

    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    @Override
    public String toString() {
        return "" + this.foodName + this.foodPrice;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodPrice() {
        return foodPrice;
    }
}
