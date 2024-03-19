package restoran.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restoran.dto.response.SimpleResponse;
import restoran.dto.response.SingInAndSingUpResponse;
import restoran.dto.request.SingInRequest;
import restoran.dto.request.WaiterRequest;
import restoran.service.UserService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApi {
    private final UserService userService1;


    @PostMapping("/register")
    public SingInAndSingUpResponse Register(@RequestBody @Valid WaiterRequest singUpRequest) {
        return userService1.save(singUpRequest);
    }

    @PostMapping("/logIn")
    public SingInAndSingUpResponse LogIn(@RequestBody @Valid SingInRequest response) {
        return userService1.LogIn(response);
    }


    @PostMapping("/sendRequest")
    public SimpleResponse sendRequest(@RequestBody @Valid WaiterRequest singUpRequest) {
        return userService1.sendRequest(singUpRequest );
    }
}


