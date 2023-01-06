package DevsChallenge.example.DevsChallenge.Repositories;

import DevsChallenge.example.DevsChallenge.Models.Role;
import DevsChallenge.example.DevsChallenge.Models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepostory extends JpaRepository<Roles,Long> {

    Optional<Roles> findByName(Role name);
}
