package gas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "gas")
public class Gas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "previous")
    private int previous;
    @Column(name = "current")
    private int current;
    @Column(name = "spent")
    private int spent;
    @Column(name = "amount")
    private float amount;
    @Column(name = "date_transferred")
    private LocalDateTime dateTransferred;
    @Column(name = "date_paid")
    private LocalDateTime datePaid;

}
