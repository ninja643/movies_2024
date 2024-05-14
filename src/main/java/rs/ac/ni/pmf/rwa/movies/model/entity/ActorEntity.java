package rs.ac.ni.pmf.rwa.movies.model.entity;


import jakarta.persistence.*;
import lombok.*;
import rs.ac.ni.pmf.rwa.movies.shared.Gender;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "actors")
public class ActorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Integer id;

    @Column(length = 32, nullable = false)
    String name;
    @Column(length = 32, nullable = false)
    String surname;

    @Enumerated(EnumType.STRING)
    Gender gender;
    String nationality;

    @Builder.Default
    @OneToMany(mappedBy = "actor", cascade = CascadeType.ALL)
    List<MovieActorEntity> movies = new ArrayList<>();

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "leadingRole", fetch = FetchType.EAGER)
    List<MovieEntity> leadingRoles = new ArrayList<>();

    public void addMainRoles(MovieEntity... movieEntities) {
        for (final MovieEntity movieEntity : movieEntities) {
            leadingRoles.add(movieEntity);
            movieEntity.setLeadingRole(this);
        }
    }


}
