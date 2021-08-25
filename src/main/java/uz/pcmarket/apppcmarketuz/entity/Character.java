package uz.pcmarket.apppcmarketuz.entity;

import lombok.*;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Product product;
}
