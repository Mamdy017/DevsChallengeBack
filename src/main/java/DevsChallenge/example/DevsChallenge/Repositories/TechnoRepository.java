package DevsChallenge.example.DevsChallenge.Repositories;

import DevsChallenge.example.DevsChallenge.Models.Technologies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnoRepository extends JpaRepository<Technologies,Long> {
}
