package rs.ac.ni.pmf.rwa.movies.service;

import org.springframework.security.core.userdetails.*;
import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.rwa.movies.repository.UsersRepository;

@RequiredArgsConstructor
public class UserService implements UserDetailsService
{
	private final UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		return usersRepository.findByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("No user with username " + username));
	}
}
