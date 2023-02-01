package DevsChallenge.example.DevsChallenge.Repositories;

import DevsChallenge.example.DevsChallenge.Models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {
    Team findByNom(String nom);
}
