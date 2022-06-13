package client;

import models.Ingredient;

import java.util.ArrayList;

public class GetIngredientsResponse {
    public boolean success;
    public ArrayList<Ingredient> data;

    public GetIngredientsResponse (boolean success, ArrayList<Ingredient> data) {
        this.success = success;
        this.data = data;
    }

    public GetIngredientsResponse () {}

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<Ingredient> getData() {
        return data;
    }

    public void setData(ArrayList<Ingredient> data) {
        this.data = data;
    }
}
