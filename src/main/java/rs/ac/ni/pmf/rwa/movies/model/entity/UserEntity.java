package rs.ac.ni.pmf.rwa.movies.model.entity;

import java.util.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails
{
	@Id
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "user_sequence")
	@SequenceGenerator(
		name = "user_sequence",
		sequenceName = "user_sequence",
		allocationSize = 1
	)
	private Long id;
	private String name;
	private String username;
	private String email;
	private String password;
	private Boolean locked;
	private Boolean enabled;

	@Builder.Default
	@ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"))
	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	Set<UserRole> roles = new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).toList();
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		return enabled;
	}
}
