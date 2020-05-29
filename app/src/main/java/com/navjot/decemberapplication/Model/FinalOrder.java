package com.navjot.decemberapplication.Model;

public class FinalOrder {


    public String finalOrderID;
    public String finalOrderPrice;
    public String finalOrderPaymentMethod;
    public String finalOrderDeliveryName;
    public String finalOrderDeliveryPhone;
    public String finalOrderDeliveryAddr;
    public String finalOrderDeliveryTown;
    public String finalOrderDeliveryCity;
    public String finalOrderDate;
    public String finalOrderTime;
    public String finalOrderedItems;


    public FinalOrder() {}

    public FinalOrder(String finalOrderID, String finalOrderPrice, String finalOrderPaymentMethod,
                      String finalOrderDeliveryName, String finalOrderDeliveryPhone, String finalOrderDeliveryAddr,
                      String finalOrderDeliveryTown, String finalOrderDeliveryCity, String finalOrderDate,
                      String finalOrderTime, String finalOrderedItems) {
        this.finalOrderID = finalOrderID;
        this.finalOrderPrice = finalOrderPrice;
        this.finalOrderPaymentMethod = finalOrderPaymentMethod;
        this.finalOrderDeliveryName = finalOrderDeliveryName;
        this.finalOrderDeliveryPhone = finalOrderDeliveryPhone;
        this.finalOrderDeliveryAddr = finalOrderDeliveryAddr;
        this.finalOrderDeliveryTown = finalOrderDeliveryTown;
        this.finalOrderDeliveryCity = finalOrderDeliveryCity;
        this.finalOrderDate = finalOrderDate;
        this.finalOrderTime = finalOrderTime;
        this.finalOrderedItems = finalOrderedItems;

    }



}
