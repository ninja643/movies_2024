package rs.ac.ni.pmf.rwa.movies.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  public static final String ADMIN = "ADMIN";
  public static final String USER = "USER";


//Authentication
@Bean
  public UserDetailsService userDetailsService(PasswordEncoder encoder) {

    UserDetails admin = User.withUsername("Predrag")
            .password(encoder.encode("111"))
            .authorities(USER, ADMIN)
            .build();

    UserDetails user = User.withUsername("Stefan")
            .password(encoder.encode("123"))
            //.roles("USER")
            .authorities(USER)
            .build();
    return new InMemoryUserDetailsManager(admin, user); // korisnike drzimo u memoriji
}


//Authorization
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
            .httpBasic(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable())
            .formLogin(Customizer.withDefaults())
            .authorizeHttpRequests(registry -> {
                      registry.requestMatchers(HttpMethod.GET, "/api/v1/movies","/api/v1/movies/").permitAll();
                      registry.requestMatchers(HttpMethod.GET,"/api/v1/movies/**").authenticated();
                      registry.requestMatchers(HttpMethod.POST, "api/v1/movies").hasAuthority(USER);
                      registry.requestMatchers(HttpMethod.PUT, "api/v1/movies/**").hasAuthority(USER);
                      registry.requestMatchers(HttpMethod.DELETE, "api/v1/movies/**").hasAuthority(ADMIN);
                      registry.anyRequest().authenticated();
            }).build();
}
  @Bean
    public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
