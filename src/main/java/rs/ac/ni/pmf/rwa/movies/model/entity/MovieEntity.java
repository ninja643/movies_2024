package rs.ac.ni.pmf.rwa.movies.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import rs.ac.ni.pmf.rwa.movies.shared.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The MovieEntity class represents a movie entity in the application.
 * It is used to store and retrieve information about movies.
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movies")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(name = "movie_title", length = 150, nullable = false)
    String name;
    String description;
    int releaseYear;

    @Enumerated(EnumType.STRING)
    Genre genre;

    @Builder.Default
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    List<MovieActorEntity> actors = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name="leading_role_id")
    ActorEntity leadingRole;

    @OneToOne(orphanRemoval = true)
    BudgetEntity movieBudget;


    /**
     * Checks if the current movie entity is equal to the specified object.
     *
     * @param o the object to compare against
     * @return true if the current movie entity is equal to the specified object, false otherwise
     */
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        MovieEntity that = (MovieEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
