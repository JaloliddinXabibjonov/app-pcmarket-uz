package uz.pcmarket.apppcmarketuz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Checkout checkout;

    @OneToOne
    private User user;

    public Basket(Checkout checkout, User user) {
        this.checkout = checkout;
        this.user = user;
    }
}
