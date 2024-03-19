package restoran.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_gen")
    @SequenceGenerator(name = "category_gen", sequenceName = "category_seq", allocationSize =1, initialValue = 1)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "category", cascade = {CascadeType.REMOVE,CascadeType.PERSIST}  )
    private List<SubCategory> subCategories;
    public void addSubCategory(SubCategory subCategory){
        if (this.subCategories == null) this.subCategories = new ArrayList<>();
        this.subCategories.add(subCategory);
    }
}
