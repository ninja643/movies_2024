package rs.ac.ni.pmf.rwa.movies.model.entity;


import jakarta.persistence.*;
import lombok.*;
import rs.ac.ni.pmf.rwa.movies.shared.Gender;

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


}
