package restoran.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.access.prepost.PreAuthorize;
import restoran.entity.MenuItem;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Entity
@Table(name = "stopLists")
@Getter @Setter
@NoArgsConstructor
public class StopList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stopList_gen")
    @SequenceGenerator(name = "stopList_gen", sequenceName = "stopList_seq", allocationSize =1, initialValue = 1)
    private Long id;
    private String reason;
    private LocalDate date= LocalDate.now();

    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE})
    private MenuItem menuItem;


}
