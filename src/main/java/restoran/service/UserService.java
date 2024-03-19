package restoran.service;

import restoran.dto.request.UserRequest;
import restoran.dto.response.SimpleResponse;
import restoran.dto.response.SingInAndSingUpResponse;
import restoran.dto.request.SingInRequest;
import restoran.dto.request.WaiterRequest;
import restoran.dto.response.UserResponse;

import java.util.List;


public interface UserService {
    SingInAndSingUpResponse save(WaiterRequest singUpRequest);

    SingInAndSingUpResponse LogIn(SingInRequest response);

    SingInAndSingUpResponse saveChef(Long  restaurantId, WaiterRequest singUpRequest);

    SingInAndSingUpResponse saveWaiter(Long  restaurantId, WaiterRequest singUpRequest);

    List<UserResponse> findAll(Long restID);

    SimpleResponse delete(Long userId);

    SimpleResponse updateUser(Long userId, UserRequest userRequest);

    UserResponse findById(Long userId);

    List<UserResponse> findAllRequest();

    SimpleResponse sendRequest(WaiterRequest singUpRequest);

    SimpleResponse assignRequest(Long restId, Long requestId);
}
