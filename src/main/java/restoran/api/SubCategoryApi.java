package restoran.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import restoran.dto.request.CategoryRequest;
import restoran.dto.request.SubCategoryRequest;
import restoran.dto.response.SimpleResponse;
import restoran.dto.response.SubCategoryResponse;
import restoran.service.SubCategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subcategory")
public class SubCategoryApi {
private final SubCategoryService subCategoryService;
    @Secured("ADMIN")
    @GetMapping("/all")
    public List<SubCategoryResponse> findAll() {
        return subCategoryService.findAll();
    }
    @Secured({"ADMIN"})
    @PostMapping("/new/{categoryId}")
    public SimpleResponse saveSubCategory(@RequestBody @Valid SubCategoryRequest subCategoryRequest, @PathVariable Long categoryId) {
        return subCategoryService.save(subCategoryRequest, categoryId);
    }
    @Secured("ADMIN")
    @GetMapping("/delete/{subCategoryId}")
    public SimpleResponse deleteSubCategory(@PathVariable @Valid Long subCategoryId){
        return subCategoryService.delete(subCategoryId);
    }
    @Secured("ADMIN")
    @PutMapping("/update/{subCategoryId}")
    public SimpleResponse updateSubCategory(@RequestBody @Valid SubCategoryRequest subCategoryRequest,@PathVariable Long subCategoryId){
        return subCategoryService.updateSubCategory(subCategoryId, subCategoryRequest);
    }
    @Secured("ADMIN")
    @GetMapping("/find/{subCategoryId}")
    public SubCategoryResponse findById(@PathVariable @Valid Long subCategoryId){
        return subCategoryService.findById(subCategoryId);
    }
    @Secured("ADMIN")
    @GetMapping("/findByCategoryId/{categoryId}")
    public SubCategoryResponse findByCategoryId(@PathVariable @Valid Long categoryId){
        return subCategoryService.findByCategoryId(categoryId);
    }
    @Secured("ADMIN")
    @GetMapping("/groupBy")
    public List<SubCategoryResponse> groupBy(){
        return subCategoryService.groupBy();
    }
}
