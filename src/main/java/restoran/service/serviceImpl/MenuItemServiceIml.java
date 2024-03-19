package restoran.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restoran.dto.request.MenuItemRequest;
import restoran.dto.response.CategoryResponse;
import restoran.dto.response.MenuItemResponse;
import restoran.dto.response.SimpleResponse;
import restoran.entity.*;
import restoran.entity.MenuItem;
import restoran.entity.enums.Vegetarian;
import restoran.exception.NotFoundException;
import restoran.repository.*;
import restoran.service.MenuItemService;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MenuItemServiceIml implements MenuItemService {
    private final RestaurantRepo restaurantRepo;
    private final MenuItemRepo menuItemRepo;
    private final CategoryRepo categoryRepo;
    private final SubCategoryRepo subCategoryRepo;
    private final StopListRepo stopListRepo;
    @Override
    public SimpleResponse saveMenu(Long restaurantId, Long categoryId, MenuItemRequest menuItemRequest) {
        Restaurant rest = restaurantRepo.findRest(restaurantId);
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new NotFoundException("Category not found"));

        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuItemRequest.getName());
        menuItem.setDescription(menuItemRequest.getDescription());
        menuItem.setPrice(menuItemRequest.getPrice());
        menuItem.setImage(menuItemRequest.getImage());
        menuItem.setIsVegetarian(menuItemRequest.getIsVegetarian());

        SubCategory subCategory = new SubCategory();
        subCategory.setName(menuItemRequest.getName()); // Assuming you have a method to get subCategoryName in MenuItemRequest
        subCategory.setCategory(category);
        subCategoryRepo.save(subCategory); // Save the SubCategory before associating it with the MenuItem

        menuItem.setSubCategory(subCategory);
        menuItem.setRestaurant(rest);
        menuItemRepo.save(menuItem);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("menu success saved").build();
    }

    @Override
    public List<MenuItemResponse> sort(String ascOrDesc) {

        List<MenuItemResponse>menuResponses = new ArrayList<>();
        if (ascOrDesc.equalsIgnoreCase("asc")){
            List<MenuItem> menuAsc = menuItemRepo.sortAsc("asc");
            for (MenuItem menuItem : menuAsc) {
                menuResponses.add(new MenuItemResponse(menuItem.getName(), menuItem.getImage(), menuItem.getPrice(), menuItem.getDescription(), menuItem.getIsVegetarian()));
            }
        }else if (ascOrDesc.equalsIgnoreCase("desc")){
            List<MenuItem>menuDesc =  menuItemRepo.sortDesc("desc");
            for (MenuItem menuItem : menuDesc) {
                menuResponses.add(new MenuItemResponse(menuItem.getName(), menuItem.getImage(), menuItem.getPrice(), menuItem.getDescription(), menuItem.getIsVegetarian()));
            }
        }
        return menuResponses;
    }

    @Override
    public List<MenuItemResponse> sortVegetarian(Vegetarian vegetarian) {
        return menuItemRepo.sortByVegetarian(vegetarian);
    }

    @Override
    public List<MenuItemResponse> search(String search) {

        return menuItemRepo.search(search);
    }

    @Override
    public List<MenuItemResponse> findAll(Long id) {
        Restaurant rest = restaurantRepo.findRest(id);
        List<MenuItemResponse> menuItemResponses = new ArrayList<>();
        for (MenuItem menuItem : rest.getMenuItems()) {

            menuItemResponses.add(new MenuItemResponse(menuItem.getName(), menuItem.getImage(), menuItem.getPrice(), menuItem.getDescription(), menuItem.getIsVegetarian()));

        }
        return menuItemResponses;
    }


    @Override
    public SimpleResponse save(MenuItemRequest menuItemRequest) {
        return null;
    }

    @Override
    public SimpleResponse delete(Long menuId) {
        MenuItem menuItem = menuItemRepo.findById(menuId).orElseThrow(() -> new NotFoundException("menuNot found"));
        List<Cheque> cheques = menuItem.getCheques();
        for (Cheque cheque : cheques) {
            cheque.getMenuItems().remove(menuItem);
        }
        menuItemRepo.delete(menuItem);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("menu success deleted")
                .build();
    }

    @Override @Transactional
    public SimpleResponse update(Long menuId, MenuItemRequest menuItemRequest) {
        MenuItem menu = menuItemRepo.findById(menuId).orElseThrow(() -> new NotFoundException("menuNot found"));
        menu.setName(menuItemRequest.getName());
        menu.setImage(menuItemRequest.getImage());
        menu.setDescription(menuItemRequest.getDescription());
        menu.setPrice(menuItemRequest.getPrice());
        menu.setIsVegetarian(menuItemRequest.getIsVegetarian());
        StopList stopList = menu.getStopList();
        if (stopList != null) {
            stopListRepo.delete(stopList);
        }
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Menu with name: "+menu.getName()+" Successfully updated")
                .build();
    }

    @Override
    public MenuItemResponse findById(Long menuId) {
        MenuItem menuItem = menuItemRepo.findById(menuId).orElseThrow(() -> new NotFoundException("menuNot found"));
        return MenuItemResponse.builder()
                .name(menuItem.getName())
                .image(menuItem.getImage())
                .price(menuItem.getPrice())
                .description(menuItem.getDescription())
                .isVegetarian(menuItem.getIsVegetarian())
                .build();

    }
}
