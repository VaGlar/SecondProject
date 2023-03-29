package gr.kariera.MindTheCode.SecondProject.SecondProject.Entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int qty;


    @OneToOne
    @JoinColumn(name="product_id")
    private Product product ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    public boolean canUpdateQty(Integer qty) {
        return qty == null || qty <= 0 || this.getProduct().hasStock(qty);
    }

    public BigDecimal getSubtotal() {
        return new BigDecimal(String.valueOf(product.getPrice())).multiply(new BigDecimal(qty));
    }

    public void addQuantity(int qty) {
        if (qty > 0) {
            this.qty = this.qty + qty;
        }
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
