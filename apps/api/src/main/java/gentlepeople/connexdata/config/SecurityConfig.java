package gentlepeople.connexdata.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
          .authorizeHttpRequests(authorizeRequest ->
            authorizeRequest
              .requestMatchers(
                AntPathRequestMatcher.antMatcher("/swagger-ui/**"),
                AntPathRequestMatcher.antMatcher(("/v3/api-docs/**"))
              ).permitAll()
              .anyRequest().authenticated()
          )
          .httpBasic(withDefaults())
          .formLogin(login -> login
                  .defaultSuccessUrl("/", true)
                  .permitAll()
          )
          .logout(withDefaults())
          .headers(
                  headersConfigurer ->
                          headersConfigurer
                                  .frameOptions(
                                          HeadersConfigurer.FrameOptionsConfig::sameOrigin
                                  )
          )
          .csrf(AbstractHttpConfigurer::disable); // in dev

        return http.build();
    }
}
