package gr.kariera.MindTheCode.SecondProject.SecondProject.DTOs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class NewOrderDto implements Serializable {
    private String address;

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    private Double discountPercentage;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
