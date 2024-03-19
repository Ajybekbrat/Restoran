package restoran.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import restoran.dto.request.CategoryRequest;
import restoran.dto.request.RestaurantRequest;
import restoran.dto.response.CategoryResponse;
import restoran.dto.response.RestaurantResponse;
import restoran.dto.response.SimpleResponse;
import restoran.service.RestaurantService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurant")
public class RestaurantApi {
    private final RestaurantService restaurantService;
    @Secured("ADMIN")
    @GetMapping("/all")
    public List<RestaurantResponse> findAll() {
        return restaurantService.findAll();
    }
    @Secured({"ADMIN"})
    @PostMapping("/new")
    public SimpleResponse saveRest(@RequestBody @Valid RestaurantRequest restaurantRequest) {
        return restaurantService.save(restaurantRequest);
    }
    @Secured("ADMIN")
    @GetMapping("/delete/{restId}")
    public SimpleResponse deleteRest(@PathVariable @Valid Long restId){
        return restaurantService.delete(restId);
    }
    @Secured("ADMIN")
    @PutMapping("/update/{restId}")
    public SimpleResponse updateRest(@RequestBody @Valid RestaurantRequest restaurantRequest,@PathVariable Long restId){
        return restaurantService.updateRest(restId, restaurantRequest);
    }
    @Secured("ADMIN")
    @GetMapping("/find/{restId}")
    public RestaurantResponse findById(@PathVariable @Valid Long restId){
        return restaurantService.findById(restId);
    }

}
