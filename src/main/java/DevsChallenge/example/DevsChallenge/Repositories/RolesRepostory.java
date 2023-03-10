package DevsChallenge.example.DevsChallenge.Repositories;

import DevsChallenge.example.DevsChallenge.Models.Role;
import DevsChallenge.example.DevsChallenge.Models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;

@Repository
public interface RolesRepostory extends JpaRepository<Roles,Long> {

    Optional<Roles> findByName(Role name);

   @Modifying
   @Transactional

    @Query(value="INSERT INTO `roles` ( `name`) VALUES ('ROLE_ADMIN'), ( 'ROLE_USER'), ( 'adminuser');",nativeQuery=true)
    void create();
}
