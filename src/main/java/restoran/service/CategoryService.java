package restoran.service;

import restoran.dto.request.CategoryRequest;
import restoran.dto.response.CategoryResponse;
import restoran.dto.response.SimpleResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse findById(Long categoryId);

    SimpleResponse updateCategory(Long categoryId, CategoryRequest categoryRequest);

    SimpleResponse delete(Long categoryId);

    SimpleResponse save(CategoryRequest categoryRequest);

    List<CategoryResponse> findAll(Long restId);
}
