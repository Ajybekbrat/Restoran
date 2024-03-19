package restoran.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class CategoryResponse {
    private String name;
    private List<SubCategoryResponse>subCategories;
}
