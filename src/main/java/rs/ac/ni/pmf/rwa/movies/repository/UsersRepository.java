package rs.ac.ni.pmf.rwa.movies.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.ni.pmf.rwa.movies.model.entity.UserEntity;

public interface UsersRepository extends JpaRepository<UserEntity, Long>
{
	Optional<UserEntity> findByUsername(String username);
}
