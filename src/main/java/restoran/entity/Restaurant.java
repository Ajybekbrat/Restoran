package restoran.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_gen")
    @SequenceGenerator(name = "restaurant_gen", sequenceName = "restaurant_seq", allocationSize =1, initialValue = 1)
    private Long id;
    private String name;
    private String location;
    private String restType;
    private int numberOfEmployees;
    private int service;

    public Restaurant( String name, String location, String restType, int numberOfEmployees, int service) {
        this.name = name;
        this.location = location;
        this.restType = restType;
        this.numberOfEmployees = numberOfEmployees;
        this.service = service;
    }

    @OneToMany(mappedBy = "restaurant", cascade = {CascadeType.DETACH, CascadeType.PERSIST})
    private List<User> users;

    @OneToMany(mappedBy = "restaurant",cascade ={CascadeType.REMOVE, CascadeType.PERSIST})

    private List<MenuItem> menuItems;


    public void addUser(User user){
        if (this.users == null) this.users = new ArrayList<>();
        this.users.add(user);
    }

    public void addMenuItem(MenuItem menuItem){
        if (this.menuItems == null) this.menuItems = new ArrayList<>();
        this.menuItems.add(menuItem);
    }
}
