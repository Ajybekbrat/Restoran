package restoran.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restoran.dto.request.CategoryRequest;
import restoran.dto.response.CategoryResponse;
import restoran.dto.response.SimpleResponse;
import restoran.dto.response.SubCategoryResponse;
import restoran.entity.Category;
import restoran.entity.MenuItem;
import restoran.entity.Restaurant;
import restoran.entity.SubCategory;
import restoran.exception.NotFoundException;
import restoran.repository.CategoryRepo;
import restoran.repository.RestaurantRepo;
import restoran.service.CategoryService;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final RestaurantRepo restaurantRepo;

    @Override
    public CategoryResponse findById(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new NotFoundException("category not found"));
        List<SubCategoryResponse> subCategoryResponses = new ArrayList<>();
        for (SubCategory subCategory : category.getSubCategories()) {
            SubCategoryResponse subCategoryResponse = new SubCategoryResponse();
            subCategoryResponse.setName(subCategory.getName());
            subCategoryResponses.add(subCategoryResponse);
        }
        CategoryResponse categoryResponse = new CategoryResponse(category.getName(), subCategoryResponses);
        return categoryResponse;
    }

    @Override
    @Transactional
    public SimpleResponse updateCategory(Long categoryId, CategoryRequest categoryRequest) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new NotFoundException("category not found"));
        category.setName(categoryRequest.getName());
        categoryRepo.save(category);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("category success updated")
                .build();
    }

    @Override
    public SimpleResponse delete(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new NotFoundException("category not found"));
        categoryRepo.delete(category);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("category success deleted")
                .build();
    }

    @Override
    public SimpleResponse save(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        categoryRepo.save(category);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("category success saved")
                .build();
    }

    @Override
    public List<CategoryResponse> findAll(Long restId) {
        Restaurant rest = restaurantRepo.findRest(restId);
        List<CategoryResponse> categoryResponses = new ArrayList<>();

        List<SubCategoryResponse>subCategories = new ArrayList<>();
        for (MenuItem menuItem : rest.getMenuItems()) {
            Category category = menuItem.getSubCategory().getCategory();
            for (SubCategory subCategory : category.getSubCategories()) {
               SubCategoryResponse subCategoryResponse = new SubCategoryResponse();
               subCategoryResponse.setName(subCategory.getName());
               subCategories.add(subCategoryResponse);

               categoryResponses.add(new CategoryResponse(category.getName(), subCategories));
            }
        }
        return categoryResponses;

    }
}
