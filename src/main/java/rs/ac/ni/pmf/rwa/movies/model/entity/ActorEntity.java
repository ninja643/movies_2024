package rs.ac.ni.pmf.rwa.movies.model.entity;


import jakarta.persistence.*;
import lombok.*;
import rs.ac.ni.pmf.rwa.movies.shared.Gender;

import java.util.List;

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
    Integer id;

    @Column(length = 32, nullable = false)
    String name;
    @Column(length = 32, nullable = false)
    String surname;

    @Enumerated(EnumType.STRING)
    Gender gender;
    String nationality;

    // TODO: This needs to be ManyToMany relation
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actor", fetch = FetchType.EAGER)
    List<MovieEntity> movies;
}
