package restoran.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import restoran.dto.response.ChequeResponse;
import restoran.entity.Category;
import restoran.entity.Cheque;
import restoran.service.ChequeService;

import java.util.List;

public interface ChequeSRepo extends JpaRepository<Cheque, Long> {

}
