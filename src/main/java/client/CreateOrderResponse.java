package client;

import models.SimpleOrder;

public class CreateOrderResponse {
    public String name;
    public SimpleOrder order;
    public boolean success;
    public String message;

    public CreateOrderResponse(String name, SimpleOrder order, boolean success, String message){
        this.name = name;
        this.order = order;
        this.success = success;
        this.message = message;
    }

    public CreateOrderResponse() {}

    public SimpleOrder getOrder() {
        return order;
    }

    public void setOrder(SimpleOrder order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
