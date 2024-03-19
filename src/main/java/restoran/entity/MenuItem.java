package restoran.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import restoran.entity.enums.Vegetarian;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "menultems")
@Getter
@Setter
@NoArgsConstructor
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menuItem_gen")
    @SequenceGenerator(name = "menuItem_gen", sequenceName = "menuItem_seq", allocationSize =1, initialValue = 1)
    private Long id;
    private String name;
    private String image;
    private int price;
    private String description;
    @Enumerated(EnumType.STRING)
    private Vegetarian isVegetarian;
    private boolean toStopList  = false;
    @ManyToOne(cascade = CascadeType.DETACH)
    private Restaurant restaurant;
    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<Cheque>cheques;
    @OneToOne(cascade = CascadeType.REMOVE)
    private StopList stopList;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST})
    private SubCategory subCategory;

}
