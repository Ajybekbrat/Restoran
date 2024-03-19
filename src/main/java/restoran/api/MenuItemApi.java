package restoran.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import restoran.dto.request.CategoryRequest;
import restoran.dto.request.MenuItemRequest;
import restoran.dto.response.CategoryResponse;
import restoran.dto.response.MenuItemResponse;
import restoran.dto.response.SimpleResponse;
import restoran.entity.enums.Vegetarian;
import restoran.service.MenuItemService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menu/")
public class MenuItemApi {
    private final MenuItemService menuItemService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF')")
    @PostMapping("save/{restaurantId}/{categoryId}")
    public SimpleResponse saveMenu(@PathVariable Long restaurantId, @PathVariable Long categoryId, @RequestBody @Valid MenuItemRequest menuItemRequest){
        menuItemService.saveMenu(restaurantId,categoryId , menuItemRequest);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("menu success saved")
                .build();
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF', 'WAITER')")
    @GetMapping("sortMenu/{ascOrDesc}")
    public List<MenuItemResponse> sortByPrice(@PathVariable @Valid String ascOrDesc){
        return menuItemService.sort(ascOrDesc);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF', 'WAITER')")
    @GetMapping("/sortVegetarian")
    public List<MenuItemResponse> sort(@RequestParam @Valid Vegetarian vegetarian){
        return menuItemService.sortVegetarian(vegetarian);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF', 'WAITER')")
    @GetMapping("/searchMenu")
    public List<MenuItemResponse> search(@RequestParam @Valid String  search){
        return menuItemService.search(search);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF')")
    @GetMapping("/all/{restId}")
    public List<MenuItemResponse> findAll(@PathVariable @Valid Long restId) {
        return menuItemService.findAll(restId);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF')")
    @GetMapping("/delete/{menuId}")
    public SimpleResponse deleteMenu(@PathVariable @Valid Long menuId){
        return menuItemService.delete(menuId);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF')")
    @PutMapping("/update/{menuId}")
    public SimpleResponse updateMenu(@RequestBody @Valid MenuItemRequest menuItemRequest,@PathVariable @Valid Long menuId){
        return menuItemService.update(menuId, menuItemRequest);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF', 'WAITER')")
    @GetMapping("/find/{menuId}")
    public MenuItemResponse findById(@PathVariable @Valid Long menuId){
        return menuItemService.findById(menuId);
    }



}
