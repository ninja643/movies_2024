package rs.ac.ni.pmf.rwa.movies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.ni.pmf.rwa.movies.model.entity.BudgetEntity;


public interface BudgetRepository extends JpaRepository<BudgetEntity,Integer> {
}
