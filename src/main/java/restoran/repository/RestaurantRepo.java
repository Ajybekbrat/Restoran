package restoran.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import restoran.dto.response.RestaurantResponse;
import restoran.entity.Category;
import restoran.entity.Restaurant;
import restoran.exception.NotFoundException;
import restoran.service.RestaurantService;

import java.util.List;

public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {

    default Restaurant findRest(Long restaurantId){
       return findById(restaurantId).orElseThrow(() -> new NotFoundException());
    }

@Query("select r.name, r.location, r.numberOfEmployees, r.restType, r.service, count(r.users) from Restaurant r  where r.id = :restId")
    Restaurant finRestById(Long restId);
@Query("Select count(u.firstName) from Restaurant  r join r.users u ")
    int countEmployee();
}
