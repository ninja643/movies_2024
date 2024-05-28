package rs.ac.ni.pmf.rwa.movies.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import rs.ac.ni.pmf.rwa.movies.model.entity.UserRole;
import rs.ac.ni.pmf.rwa.movies.repository.UsersRepository;
import rs.ac.ni.pmf.rwa.movies.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration
{
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Bean
	UserDetailsService getUserDetailsService(final UsersRepository usersRepository)
	{
		return new UserService(usersRepository);
	}

	@Bean
	public AuthenticationManager getAuthenticationManager(
		final HttpSecurity http,
		final PasswordEncoder passwordEncoder,
		final UserDetailsService userDetailsService) throws Exception
	{
		final AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
		builder
			.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder);

		return builder.build();
	}

	//Authorization
	@Bean
	public SecurityFilterChain securityFilterChain(
		final HttpSecurity httpSecurity,
		final AuthenticationManager authenticationManager) throws Exception
	{
		return httpSecurity
			.csrf(AbstractHttpConfigurer::disable)
			.httpBasic(Customizer.withDefaults())
			//			.formLogin(Customizer.withDefaults())
			.authenticationManager(authenticationManager)
			.authorizeHttpRequests(registry -> {
				registry.requestMatchers(HttpMethod.GET, "/api/v1/movies", "/api/v1/movies/").permitAll();
				registry.requestMatchers(HttpMethod.GET, "/api/v1/movies/**").authenticated();
				registry.requestMatchers(HttpMethod.POST, "api/v1/movies").hasAuthority(UserRole.ADMIN.name());
				registry.requestMatchers(HttpMethod.PUT, "api/v1/movies/**").hasAuthority(UserRole.USER.name());
				registry.requestMatchers(HttpMethod.DELETE, "api/v1/movies/**").hasAuthority(UserRole.ADMIN.name());
				registry.anyRequest().authenticated();
			})
			.sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.build();
	}
}
