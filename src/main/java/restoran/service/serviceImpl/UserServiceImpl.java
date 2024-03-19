package restoran.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restoran.config.jwt.JwtService;
import restoran.dto.request.UserRequest;
import restoran.dto.response.SimpleResponse;
import restoran.dto.response.SingInAndSingUpResponse;
import restoran.dto.request.SingInRequest;
import restoran.dto.request.WaiterRequest;
import restoran.dto.response.UserResponse;
import restoran.entity.Restaurant;
import restoran.entity.enums.Role;
import restoran.entity.User;
import restoran.exception.NotFoundException;
import restoran.exception.AlreadyExistsException;
import restoran.repository.RestaurantRepo;
import restoran.repository.UserRepo;
import restoran.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserRepo userRepo;
    private final RestaurantRepo restaurantRepo;


    @Override
    public SingInAndSingUpResponse save(WaiterRequest singUpRequest) {
        return null;
    }

    @Override
    public SingInAndSingUpResponse LogIn(SingInRequest request) {
        User user = userRepo.findByEmail(request.getEmail()).orElseThrow(() ->
                new NotFoundException("incorrect email"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new NotFoundException(String.format("incorrect password"));
    }

        return SingInAndSingUpResponse.builder()
                .email(user.getEmail())
                .role(user.getRole())
                .token(jwtService.createToken(user))
                .httpStatus(HttpStatus.OK)
                .message("sing in")
                .build();
    }

    @Override
    public SingInAndSingUpResponse saveChef(Long restaurantId, WaiterRequest request) {
        Restaurant rest = restaurantRepo.findRest(restaurantId);

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setEmail(request.getEmail());
        if (userRepo.existsByEmail(request.getEmail())) {
            throw new AlreadyExistsException("User with " + request.getEmail() + " already exists");
        }
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setExperience(request.getExperience());
        user.setAge(request.getAge());
        user.setRole(Role.CHEF);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRestaurant(rest);
        userRepo.save(user);

        return SingInAndSingUpResponse.builder()
                .email(user.getEmail())
                .role(user.getRole())
                .token(jwtService.createToken(user))
                .httpStatus(HttpStatus.OK)
                .message("Success")
                .build();
    }
    @Override
    public SingInAndSingUpResponse saveWaiter(Long  restaurantId, WaiterRequest request) {
        Restaurant rest = restaurantRepo.findRest(restaurantId);
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setEmail(request.getEmail());
        if (userRepo.existsByEmail(request.getEmail())) {
            throw new AlreadyExistsException("User with " + request.getEmail() + " already exists");
        }
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setExperience(request.getExperience());
        user.setAge(request.getAge());
        user.setRole(Role.WAITER);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRestaurant(rest);
        userRepo.save(user);
        return SingInAndSingUpResponse.builder()
                .email(user.getEmail())
                .role(user.getRole())
                .token(jwtService.createToken(user))
                .httpStatus(HttpStatus.OK)
                .message("Success")
                .build();
    }

    @Override
    public List<UserResponse> findAll(Long restID) {
        Restaurant rest = restaurantRepo.findRest(restID);

        List<UserResponse>userResponses = new ArrayList<>();
        for (User user : rest.getUsers()) {
            userResponses.add(new UserResponse(user.getId(), user.getLastName(), user.getFirstName(), user.getAge(), user.getEmail(), user.getPhoneNumber(),user.getRole(), user.getExperience()));
        }
        return userResponses;
    }


    @Override
    public SimpleResponse delete(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new NotFoundException("user not found"));
        if (user.getId() == userId || user.getRole().equals(Role.ADMIN)){
            userRepo.delete(user);
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("successfully deleted").build();
        }
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.CONFLICT)
                .message("delete failed").build();
    }
    @Override
    public SimpleResponse updateUser(Long userId, UserRequest userRequest) {
        User user = userRepo.findById(userId).orElseThrow(() -> new NotFoundException("user not found"));
        if (user.getId() == userId || user.getRole().equals(Role.ADMIN)){

            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            user.setEmail(userRequest.getEmail());
            user.setAge(userRequest.getAge());
            user.setExperience(userRequest.getExperience());
            user.setPhoneNumber(userRequest.getPhoneNumber());
            userRepo.save(user);
           return SimpleResponse.builder()
                   .httpStatus(HttpStatus.OK)
                   .message("successfully updated").build();
        }
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.CONFLICT)
                .message("update failed").build();
    }

    @Override
    public UserResponse findById(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new NotFoundException("user not found"));
        UserResponse userResponse = new UserResponse(user.getId(), user.getLastName(), user.getFirstName(), user.getAge(), user.getEmail(), user.getPhoneNumber(),user.getRole(), user.getExperience());
        return userResponse;
    }

    @Override
    public List<UserResponse> findAllRequest() {

        List<User> allRequest =userRepo.findAllRequest();
        List<UserResponse>userResponses = new ArrayList<>();
        for (User user : allRequest) {
            userResponses.add(new UserResponse(user.getId(), user.getLastName(), user.getFirstName(), user.getAge(), user.getEmail(), user.getPhoneNumber(),user.getRole(), user.getExperience()));
        }
        return userResponses;
    }

    @Override
    public SimpleResponse sendRequest(WaiterRequest request) {
if (restaurantRepo.countEmployee() <15){
    User user = new User();
    user.setFirstName(request.getFirstName());
    user.setEmail(request.getEmail());
    user.setLastName(request.getLastName());
    user.setPhoneNumber(request.getPhoneNumber());
    user.setExperience(request.getExperience());
    user.setAge(request.getAge());
    user.setRole(Role.WAITER);
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    userRepo.save(user);
    return SimpleResponse.builder()
            .httpStatus(HttpStatus.OK)
            .message("success send")

            .build();
}else {
    return SimpleResponse.builder()
            .httpStatus(HttpStatus.OK)
            .message("no please to work in restaurant")

            .build();
}

    }

    @Override @Transactional
    public SimpleResponse assignRequest(Long restId, Long requestId) {
        Restaurant rest = restaurantRepo.findRest(restId);
        User user = userRepo.findById(requestId).orElseThrow(() -> new NotFoundException("user not found"));
        user.setRestaurant(rest);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("success saved")
                .build();
    }

}
