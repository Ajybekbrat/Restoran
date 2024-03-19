package restoran.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cheques")
@Getter
@Setter
@NoArgsConstructor
public class Cheque {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cheque_gen")
    @SequenceGenerator(name = "cheque_gen", sequenceName = "cheque_seq", allocationSize =1, initialValue = 1)
    private Long id;
    private int totalPrice;
    private ZonedDateTime createdAt =ZonedDateTime.now();
    private int service;


    public int procient = 20;

    @ManyToMany(cascade = CascadeType.DETACH)
    private List<MenuItem> menuItems = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.DETACH)
    private User user;

    public void addMenuItem(MenuItem menuItem){
        if (this.menuItems == null) this.menuItems = new ArrayList<>();
        this.menuItems.add(menuItem);
    }
}
