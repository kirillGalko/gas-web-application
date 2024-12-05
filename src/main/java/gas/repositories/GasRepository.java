package gas.repositories;

import gas.model.Gas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
@Repository
public interface GasRepository extends JpaRepository<Gas, Integer> {

    Optional<Gas> findTopByOrderByCurrentDesc();
}
