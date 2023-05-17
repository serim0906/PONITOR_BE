package pebite.Ponitor_BE.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import pebite.Ponitor_BE.security.JwtAccessDeniedHandler;
import pebite.Ponitor_BE.security.JwtAuthenticationEntryPoint;
import pebite.Ponitor_BE.security.JwtSecurityConfigurer;
import pebite.Ponitor_BE.security.JwtTokenProvider;


@Slf4j
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    @Order(2)
    public SecurityFilterChain securityfilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                // .antMatchers(HttpMethod.GET,"/users/login").permitAll()
                .antMatchers("/**").permitAll()
                // .anyRequest().authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin().disable()
                .csrf().disable()
                .headers().disable()
                .httpBasic().disable()
                .rememberMe().disable()
                .logout().disable()
                .exceptionHandling()
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .apply(new JwtSecurityConfigurer(jwtTokenProvider));

        return http.build();
    }

    @Bean
    @Order(1)
    public SecurityFilterChain exceptionSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                // .authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll())
                .requestCache().disable()
                .securityContext().disable()
                .sessionManagement().disable();

        return http.build();
    }

}