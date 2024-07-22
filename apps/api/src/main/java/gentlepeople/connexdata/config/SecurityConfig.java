package gentlepeople.connexdata.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http
      .authorizeHttpRequests(authorizeRequest ->
          authorizeRequest
//              .requestMatchers(
//                AntPathRequestMatcher.antMatcher("/swagger-ui/**"),
//                AntPathRequestMatcher.antMatcher(("/v3/api-docs/**"))
//              ).permitAll()
//              .anyRequest().authenticated()
            .anyRequest().permitAll()
      )
      .httpBasic(AbstractHttpConfigurer::disable)  // in dev
      .formLogin(AbstractHttpConfigurer::disable)  // in dev
      .logout(AbstractHttpConfigurer::disable)     // in dev
//      .httpBasic(withDefaults())
//      .formLogin(login -> login
//        .defaultSuccessUrl("/", true)
//        .permitAll()
//      )
//      .logout(withDefaults())
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
