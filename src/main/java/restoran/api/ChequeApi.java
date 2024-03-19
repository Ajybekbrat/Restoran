package restoran.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import restoran.dto.request.ChequeRequest;
import restoran.dto.response.ChequeResponse;
import restoran.dto.response.SimpleResponse;
import restoran.service.ChequeService;

@RestController
@RequestMapping("/api/cheque")
@RequiredArgsConstructor

public class ChequeApi {
    private final ChequeService chequeService;


    @PreAuthorize("hasAnyAuthority('ADMIN', 'WAITER')")
    @PostMapping("/{waiterId}")
    public SimpleResponse giveCheque(@PathVariable Long waiterId,
                                     @RequestBody @Valid ChequeRequest chequeRequest){
        return  chequeService.giveCheck(waiterId,chequeRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'WAITER')")
    @GetMapping("/findCheck/{cheId}")
    public ChequeResponse findCheckById(@PathVariable @Valid Long cheId){
        return chequeService.findCheck(cheId);
    }
    @Secured("ADMIN")
    @GetMapping("/delete/{chequeId}")
    public SimpleResponse deleteCheck(@PathVariable @Valid Long chequeId){
        return chequeService.delete(chequeId);
    }

}
