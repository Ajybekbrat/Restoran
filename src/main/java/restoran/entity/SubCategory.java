package restoran.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subCategories")
@Getter
@Setter
@NoArgsConstructor
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subCategory_gen")
    @SequenceGenerator(name = "subCategory_gen", sequenceName = "subCategory_seq", allocationSize =1, initialValue = 1)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.REMOVE)
    private List<MenuItem> menuItems;
@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST})
private Category category;
    public void addMenuItem(MenuItem menuItem){
        if (this.menuItems == null) this.menuItems = new ArrayList<>();
        this.menuItems.add(menuItem);
    }
}
