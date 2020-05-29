package com.navjot.decemberapplication.Model;

public class CartVegetables {

    private String vname;
    private String vprice;
    private String vquantity;
    private String vtprice;

    public CartVegetables() {
    }

    public CartVegetables(String vname, String vprice, String vquantity,String vtprice) {
        this.vname = vname;
        this.vprice = vprice;
        this.vtprice = vtprice;
        this.vquantity = vquantity;
    }

    public String getVtprice() {
        return vtprice;
    }

    public void setVtprice(String vtprice) {
        this.vtprice = vtprice;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getVprice() {
        return vprice;
    }

    public void setVprice(String vprice) {
        this.vprice = vprice;
    }

    public String getVquantity() {
        return vquantity;
    }

    public void setVquantity(String vquantity) {
        this.vquantity = vquantity;
    }
}


