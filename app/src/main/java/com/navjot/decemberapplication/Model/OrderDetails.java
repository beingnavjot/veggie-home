package com.navjot.decemberapplication.Model;

public class OrderDetails {


    private String orderTprice;
    private String orderDate;
    private String orderTime;
    private String orderedItems;
    private String orderId;


    public OrderDetails() {
        this.orderTprice = orderTprice;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderedItems = orderedItems;
        this.orderId = orderId;
    }


    public String getOrderedItems() {
        return orderedItems;
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setOrderedItems(String orderedItems) {
        this.orderedItems = orderedItems;
    }

    public String getOrderTprice() {
        return orderTprice;
    }

    public void setOrderTprice(String orderTprice) {
        this.orderTprice = orderTprice;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
}
