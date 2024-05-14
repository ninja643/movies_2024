package rs.ac.ni.pmf.rwa.movies.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "movies_actors")
public class MovieActorEntity {

    @EmbeddedId
    private MovieActorPK id;

    private double salary;

    @ManyToOne
    @MapsId("movieId")
    private MovieEntity movie;

    @ManyToOne
    @MapsId("actorId")
    private ActorEntity actor;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        MovieActorEntity that = (MovieActorEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }

    @Embeddable
    @Getter
    @Setter
    public static class MovieActorPK implements Serializable {
        @Column(name = "movie_id")
        int movieId;

        @Column(name = "actor_id")
        int actorId;

        @Override
        public final boolean equals(Object o) {
            if (this == o) return true;
            if (o == null) return false;
            Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
            Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
            if (thisEffectiveClass != oEffectiveClass) return false;
            MovieActorPK that = (MovieActorPK) o;

            return Objects.equals(movieId, that.movieId) && Objects.equals(actorId, that.actorId);
        }

        @Override
        public final int hashCode() {
            return Objects.hash(movieId, actorId);
        }
    }
}
