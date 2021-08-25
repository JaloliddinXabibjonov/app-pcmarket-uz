package uz.pcmarket.apppcmarketuz.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String email;

    @Column(nullable = false, unique = true)
    private String password;
}
