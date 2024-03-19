package restoran.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import restoran.dto.request.CategoryRequest;
import restoran.dto.request.StopListRequest;
import restoran.dto.response.CategoryResponse;
import restoran.dto.response.SimpleResponse;
import restoran.dto.response.StopListREsponse;
import restoran.entity.StopList;
import restoran.repository.MenuItemRepo;
import restoran.repository.StopListRepo;
import restoran.service.MenuItemService;
import restoran.service.StopListService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stopList")
public class StopListApi {
    private final MenuItemService menuItemService;
    private final StopListService stopListService;

    @PostMapping("/toStopList/{menuId}")
    public SimpleResponse toStopList(@PathVariable @Valid Long menuId, @RequestBody @Valid  StopListRequest stopListRequest){
        return stopListService.toStop(menuId, stopListRequest );
    }
    @Secured("ADMIN")
    @GetMapping("/all")
    public List<StopListREsponse> findAll() {
        return stopListService.findAll();
    }

    @PutMapping("/update/{stopListId}")
    public SimpleResponse updateStopList(@RequestBody @Valid StopListRequest stopListRequest, @PathVariable Long stopListId){
        return stopListService.update(stopListRequest, stopListId);
    }
    @Secured("ADMIN")
    @GetMapping("/find/{stopListId}")
    public StopListREsponse findById(@PathVariable @Valid Long stopListId){
        return stopListService.findById(stopListId);
    }
    @Secured("ADMIN")
    @GetMapping("/delete/{stopListId}")
    public SimpleResponse deleteStopList(@PathVariable @Valid Long stopListId){
        return stopListService.delete(stopListId);
    }



}
