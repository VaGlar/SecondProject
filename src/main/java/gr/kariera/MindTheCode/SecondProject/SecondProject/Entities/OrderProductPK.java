package gr.kariera.MindTheCode.SecondProject.SecondProject.Entities;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
@Embeddable
public class OrderProductPK {

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "product_id")
    private Integer productId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}