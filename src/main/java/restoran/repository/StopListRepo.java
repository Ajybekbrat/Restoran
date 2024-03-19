package restoran.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import restoran.entity.StopList;

public interface StopListRepo extends JpaRepository<StopList, Long> {
    @Modifying
    @Transactional
    @Query("delete from StopList  s where s.id = :stopListId")
    void deleteStop(Long stopListId);
}
