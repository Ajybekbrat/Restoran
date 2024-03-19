package restoran.service;

import restoran.dto.request.RestaurantRequest;
import restoran.dto.response.RestaurantResponse;
import restoran.dto.response.SimpleResponse;

import java.util.List;

public interface RestaurantService {

    List<RestaurantResponse> findAll();

    SimpleResponse save(RestaurantRequest restaurantRequest);

    SimpleResponse delete(Long restId);

    SimpleResponse updateRest(Long restId, RestaurantRequest restaurantRequest);

    RestaurantResponse findById(Long restId);
}
