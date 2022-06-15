package client;

import java.util.ArrayList;

public class CreateOrderRequest {
    public ArrayList<String> ingredients;

    public CreateOrderRequest(ArrayList<String> ingredients){
        this.ingredients = ingredients;
    }

    public  CreateOrderRequest() {}

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }
}
