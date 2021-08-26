package uz.pcmarket.apppcmarketuz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private double price;

    @ManyToOne
    private Category category;

    @OneToOne
    private Attachment attachment;


    public Product(String name, double price, Category category, Attachment attachment) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.attachment = attachment;
    }
}
