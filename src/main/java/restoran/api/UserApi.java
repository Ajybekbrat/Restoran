package restoran.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import restoran.dto.request.UserRequest;
import restoran.dto.request.WaiterRequest;
import restoran.dto.response.SimpleResponse;
import restoran.dto.response.SingInAndSingUpResponse;
import restoran.dto.response.UserResponse;
import restoran.service.UserService;

import java.util.List;
import java.util.logging.Level;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApi {
    private final UserService userService;
    @Secured("ADMIN")
    @PostMapping("/saveChef/{restaurantId}")
    public SingInAndSingUpResponse saveChef(@PathVariable @Valid Long restaurantId,@RequestBody @Valid WaiterRequest singUpRequest) {
        return userService.saveChef( restaurantId,singUpRequest);

    }
    @Secured("ADMIN")
    @PostMapping("/saveWaiter/{restaurantId}")
    public SingInAndSingUpResponse saveWaiter(@PathVariable @Valid Long restaurantId, @RequestBody @Valid WaiterRequest singUpRequest) {
        return userService.saveWaiter( restaurantId,singUpRequest);
    }
    @Secured("ADMIN")
    @GetMapping("/allRequest")
    public List<UserResponse>findAllRequest(){
        return userService.findAllRequest();
    }
    @Secured("ADMIN")
    @PostMapping("/assignRequest/{restId}/{requestId}")
    public SimpleResponse assignWorker(@PathVariable @Valid Long restId,@PathVariable @Valid Long requestId ){
      return   userService.assignRequest(restId, requestId);
    }


    @Secured("ADMIN")
    @GetMapping("/all/{restId}")
    public List<UserResponse> findAll(@PathVariable @Valid  Long restId) {
        return userService.findAll(restId);
    }


    @Secured("ADMIN")
    @GetMapping("/delete/{userId}")
    public SimpleResponse deleteUser(@PathVariable @Valid Long userId){
        return userService.delete(userId);
    }
    @Secured("ADMIN")
    @PutMapping("/update/{userId}")
    public SimpleResponse updateUser(@RequestBody @Valid UserRequest userRequest, @PathVariable @Valid Long userId){
        return userService.updateUser(userId, userRequest);
    }
    @Secured("ADMIN")
    @GetMapping("/find/{userId}")
    public UserResponse findById(@PathVariable @Valid Long userId){
        return userService.findById(userId);
    }


}
