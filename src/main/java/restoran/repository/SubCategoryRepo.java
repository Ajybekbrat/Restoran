package restoran.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import restoran.dto.response.SubCategoryResponse;
import restoran.entity.Category;
import restoran.entity.SubCategory;
import restoran.service.SubCategoryService;

import java.util.List;

public interface SubCategoryRepo extends JpaRepository<SubCategory, Long> {
    @Query("select c.name, s.name from Category c inner join SubCategory s on c.id = s.category.id  group by c.name")
    List<SubCategory> groupBy();
}
