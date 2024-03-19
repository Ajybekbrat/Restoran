package restoran.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import restoran.dto.response.MenuCheckResponse;
import restoran.dto.response.MenuItemResponse;
import restoran.entity.Category;
import restoran.entity.MenuItem;
import restoran.entity.Restaurant;
import restoran.entity.StopList;
import restoran.entity.enums.Vegetarian;
import restoran.exception.NotFoundException;
import restoran.service.MenuItemService;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public interface MenuItemRepo extends JpaRepository<MenuItem, Long> {
    @Query("SELECT s FROM MenuItem s ORDER BY s.price asc")
    List<MenuItem> sortAsc(String ascOrDesc);
    @Query("SELECT s FROM MenuItem s ORDER BY s.price desc")
    List<MenuItem> sortDesc(String ascOrDesc);
    @Query("SELECT new restoran.dto.response.MenuItemResponse( c.name,c.image,c.price, c.description, c.isVegetarian) from MenuItem  c where c.isVegetarian = :vegetarian")

    List<MenuItemResponse> sortByVegetarian(Vegetarian vegetarian);
    @Query("select new restoran.dto.response.MenuItemResponse(c.name, c.image, c.price, c.description, c.isVegetarian) from MenuItem c where c.name like %:search%")
    List<MenuItemResponse> search(@Param("search") String search);

    @Transactional
    void deleteByStopList(StopList stopList);
    @Query("select m from MenuItem m where  m.id in (:menu)")
    List<MenuItem> getAllMenuId(List<Long> menu);
    @Query("""
       SELECT new restoran.dto.response.MenuCheckResponse(m.name,m.price,m.description,m.isVegetarian)
       FROM MenuItem m join  m.restaurant r join r.users u join m.cheques c where  c.id =:cheId
       """)
    List<MenuCheckResponse> checkById(Long cheId);
}
