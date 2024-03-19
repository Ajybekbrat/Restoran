package restoran.service.serviceImpl;

import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import restoran.config.jwt.JwtService;
import restoran.dto.request.RestaurantRequest;
import restoran.dto.response.RestaurantResponse;
import restoran.dto.response.SimpleResponse;
import restoran.entity.MenuItem;
import restoran.entity.Restaurant;
import restoran.entity.enums.Role;
import restoran.entity.User;
import restoran.exception.NotFoundException;
import restoran.repository.RestaurantRepo;
import restoran.repository.UserRepo;
import restoran.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepo restaurantRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserRepo userRepo;

    @PostConstruct
    public void initMethod() {
        Restaurant restaurant = new Restaurant(
                "NAVAT", "CHUI 82", "EVROPEAN", 15, 10);
        restaurantRepo.save(restaurant);
    }

    @PostConstruct
    public void initUser() {
        Restaurant rest = restaurantRepo.findById(Long.valueOf(1)).orElseThrow(() -> new NotFoundException());
        User user = new User("admin", "Admin",
               25,
                "admin@gmail.com", passwordEncoder.encode("admin"),
                "+996502091107", Role.ADMIN, 12, rest);
        userRepo.save(user);
    }

    @Override
    public List<RestaurantResponse> findAll() {
        List<Restaurant> all = restaurantRepo.findAll();
        List<RestaurantResponse> restaurantResponses = new ArrayList<>();
        int i = restaurantRepo.countEmployee();
        for (Restaurant restaurant : all) {
            restaurantResponses.add(new RestaurantResponse(restaurant.getId(),
                    restaurant.getName(), restaurant.getLocation(), restaurant.getRestType()
                    , i, restaurant.getService()));
        }
        return restaurantResponses;
    }

    @Override
    public SimpleResponse save(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantRequest.getName());
        restaurant.setService(restaurantRequest.getService());
        restaurant.setRestType(restaurantRequest.getRestType());
        restaurant.setLocation(restaurantRequest.getLocation());
        restaurant.setNumberOfEmployees(restaurantRequest.getNumberOfEmployees());
        restaurantRepo.save(restaurant);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("restaurant saved")
                .build();
    }

    @Override
    public SimpleResponse delete(Long restId) {
        Restaurant rest = restaurantRepo.findById(Long.valueOf(restId)).orElseThrow(() -> new NotFoundException("restaurant not found"));
        for (User user : rest.getUsers()) {
            if (user != null) {
                user.setRestaurant(null);
            }
        }
        restaurantRepo.delete(rest);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("restaurant deleted")
                .build();
    }


    @Override
    public SimpleResponse updateRest(Long restId, RestaurantRequest restaurantRequest) {
        Restaurant rest = restaurantRepo.findById(Long.valueOf(restId)).orElseThrow(() -> new NotFoundException("restaurant not found"));
rest.setName(restaurantRequest.getName());
rest.setLocation(restaurantRequest.getLocation());
rest.setRestType(restaurantRequest.getRestType());
rest.setService(restaurantRequest.getService());
restaurantRepo.save(rest);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("restaurant updated")
                .build();
    }

    @Override
    public RestaurantResponse findById(Long restId) {
      //  restaurantRepo.finRestById(restId);
         return null;
    }
}
