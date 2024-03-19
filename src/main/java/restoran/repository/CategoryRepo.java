package restoran.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import restoran.entity.Category;
import restoran.service.CategoryService;

public interface CategoryRepo extends JpaRepository<Category, Long>{
}
