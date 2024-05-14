package rs.ac.ni.pmf.rwa.movies.model.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import rs.ac.ni.pmf.rwa.movies.shared.Gender;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="actors")
public class ActorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    Integer id;

    @Column(length = 32, nullable = false)
    String name;
    @Column(length = 32, nullable = false)
    String surname;

    @Enumerated(EnumType.STRING)
    Gender gender;
    String nationality;

    // TODO: This needs to be ManyToMany relation
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actor", fetch = FetchType.EAGER)
    List<MovieEntity> movies = new ArrayList<>();
    @Builder.Default
   @OneToMany (cascade =  CascadeType.ALL, mappedBy = "leadingRole", fetch = FetchType.EAGER)
  List<MovieEntity> leadingRoles = new ArrayList<>();

    public void addMainRoles(MovieEntity... movieEntities) {
        for (final MovieEntity movieEntity: movieEntities ){
            leadingRoles.add(movieEntity);
            movieEntity.setLeadingRole(this);
        }
    }


}
