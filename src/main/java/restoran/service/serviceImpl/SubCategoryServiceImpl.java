package restoran.service.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import restoran.dto.request.SubCategoryRequest;
import restoran.dto.response.SimpleResponse;
import restoran.dto.response.SubCategoryResponse;
import restoran.entity.Category;
import restoran.entity.SubCategory;
import restoran.exception.NotFoundException;
import restoran.repository.CategoryRepo;
import restoran.repository.SubCategoryRepo;
import restoran.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubCategoryServiceImpl implements SubCategoryService {
    private final SubCategoryRepo subCategoryRepo;
    private final CategoryRepo categoryRepo;
    @Override
    public List<SubCategoryResponse> findAll() {
         List<SubCategoryResponse>subCategoryResponses = new ArrayList<>();
        List<SubCategory> all = subCategoryRepo.findAll();
        for (SubCategory subCategory : all) {
            SubCategoryResponse subCategoryResponse = new SubCategoryResponse();
            subCategoryResponse.setName(subCategory.getName());
            subCategoryResponses.add(subCategoryResponse);

        }
        return subCategoryResponses;
    }

    @Override
    public SimpleResponse save(SubCategoryRequest categoryRequest, Long id) {
        SubCategory subCategory = new SubCategory();
        subCategory.setName(categoryRequest.getName());
        Category category = categoryRepo.findById(id).orElseThrow(() -> new NotFoundException("category not found "));
        subCategory.setCategory(category);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("success saved")
                .build();
    }


    @Override
    public SimpleResponse delete(Long categoryId) {
        SubCategory subCategory = subCategoryRepo.findById(categoryId).orElseThrow(() -> new NotFoundException("sub category not found "));
subCategoryRepo.delete(subCategory);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("success deleted")
                .build();
    }

    @Override @Transactional
    public SimpleResponse updateSubCategory(Long subCategoryId, SubCategoryRequest subCategoryRequest) {
        SubCategory subCategory = subCategoryRepo.findById(subCategoryId).orElseThrow(() -> new NotFoundException("sub category not found "));
subCategory.setName(subCategoryRequest.getName());
subCategoryRepo.save(subCategory);
        return  SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("success updated")
                .build();
    }

    @Override
    public SubCategoryResponse findById(Long subCategoryId) {
        SubCategory subCategory = subCategoryRepo.findById(subCategoryId).orElseThrow(() -> new NotFoundException("sub category not found "));
        SubCategoryResponse subCategoryResponse = new SubCategoryResponse();
        subCategoryResponse.setName(subCategory.getName());

        return subCategoryResponse;
    }

    @Override
    public SubCategoryResponse findByCategoryId(Long categoryId) {
        SubCategoryResponse subCategoryResponse=new SubCategoryResponse();
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new NotFoundException("category not found "));
        for (SubCategory subCategory : category.getSubCategories()) {
            subCategoryResponse.setName(subCategory.getName());
        }

        return subCategoryResponse;
    }

    @Override
    public List<SubCategoryResponse> groupBy() {
        List<SubCategoryResponse>subCategoriesRes = new ArrayList<>();
        List<SubCategory> subCategories = subCategoryRepo.groupBy();
        for (SubCategory subCategory : subCategories) {
            SubCategoryResponse  subCategoryResponse = new SubCategoryResponse();
            subCategoryResponse.setName(subCategory.getName());
            subCategoriesRes.add(subCategoryResponse);
        }

        return subCategoriesRes;
    }
}
