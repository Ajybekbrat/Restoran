package restoran.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import restoran.dto.request.CategoryRequest;
import restoran.dto.response.CategoryResponse;
import restoran.dto.response.SimpleResponse;
import restoran.service.CategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryApi {
    private final CategoryService categoryService;
    //CRUD
    @Secured("ADMIN")
    @GetMapping("/all/{restId}")
    public List<CategoryResponse> findAll(@PathVariable @Valid Long restId) {
        return categoryService.findAll(restId);
    }
    @Secured({"ADMIN"})
    @PostMapping("/new")
    public SimpleResponse saveCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
        return categoryService.save(categoryRequest);
    }
    @Secured("ADMIN")
    @GetMapping("/delete/{categoryId}")
    public SimpleResponse deleteCategory(@PathVariable @Valid Long categoryId){
        return categoryService.delete(categoryId);
    }
    @Secured("ADMIN")
    @PutMapping("/update/{categoryId}")
    public SimpleResponse updateCategory(@RequestBody @Valid CategoryRequest categoryRequest,@PathVariable Long categoryId){
        return categoryService.updateCategory(categoryId, categoryRequest);
    }
    @Secured("ADMIN")
    @GetMapping("/find/{categoryId}")
    public CategoryResponse findById(@PathVariable @Valid Long categoryId){
        return categoryService.findById(categoryId);
    }


}
