package gr.kariera.MindTheCode.SecondProject.SecondProject.Entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    private Integer productId;
    private BigDecimal quantity;

    public OrderProduct(Integer productId, BigDecimal quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
}
