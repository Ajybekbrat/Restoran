package restoran.service;

import restoran.dto.request.MenuItemRequest;
import restoran.dto.response.CategoryResponse;
import restoran.dto.response.MenuItemResponse;
import restoran.dto.response.SimpleResponse;
import restoran.entity.enums.Vegetarian;

import java.util.List;

public interface MenuItemService {
    SimpleResponse saveMenu(Long restaurantId,Long categoryId, MenuItemRequest menuItemRequest);

    List<MenuItemResponse> sort(String ascOrDesc);

    List<MenuItemResponse> sortVegetarian(Vegetarian vegetarian);

    List<MenuItemResponse> search(String search);

    List<MenuItemResponse> findAll(Long id);

    SimpleResponse save(MenuItemRequest menuItemRequest);

    SimpleResponse delete(Long menuId);

    SimpleResponse update(Long menuId, MenuItemRequest menuItemRequest);

    MenuItemResponse findById(Long menuId);
}
