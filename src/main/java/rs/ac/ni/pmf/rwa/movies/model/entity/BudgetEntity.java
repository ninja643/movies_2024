package rs.ac.ni.pmf.rwa.movies.model.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Transactional
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "budget")
public class BudgetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "budget_id")
    Integer budgetId;

    Double budget;

    @OneToOne(mappedBy = "movieBudget", cascade = CascadeType.ALL)
    MovieEntity movieEntity;


}
