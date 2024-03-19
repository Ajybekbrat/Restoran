package restoran.service;

import restoran.dto.request.SubCategoryRequest;
import restoran.dto.response.SimpleResponse;
import restoran.dto.response.SubCategoryResponse;

import java.util.List;

public interface SubCategoryService {
    List<SubCategoryResponse> findAll();

    SimpleResponse save(SubCategoryRequest categoryRequest, Long id);

    SimpleResponse delete(Long categoryId);

    SimpleResponse updateSubCategory(Long subCategoryId, SubCategoryRequest subCategoryRequest);

    SubCategoryResponse findById(Long subCategoryId);

    SubCategoryResponse findByCategoryId(Long categoryId);

    List<SubCategoryResponse> groupBy();
}
