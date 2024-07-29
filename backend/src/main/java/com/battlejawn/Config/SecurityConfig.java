package com.battlejawn.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;
    private final UserAuthenticationProvider userAuthenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().authenticationEntryPoint(userAuthenticationEntryPoint)
                .and()
                .addFilterBefore(new JwtAuthFilter(userAuthenticationProvider), BasicAuthenticationFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(HttpMethod.POST,
                                "/login",
                                "/register",
                                "/api/battle-session/create",
                                "/api/battle-session/end",
                                "/api/enemy",
                                "/api/enemy-move",
                                "/api/hero",
                                "/api/hero-move",
                                "/api/hero/rest/{id}",
                                "/api/store/buy",
                                "/api/store/sell",
                                "/api/inventory/add/{id}",
                                "/api/inventory/potion/{id}",
                                "/api/inventory/water/{id}",
                                "/api/talent/activate",
                                "/api/talent/reset"
                        ).permitAll()
                        .requestMatchers(HttpMethod.GET,
                                "/api/battle-history-message/{id}",
                                "/api/battle-session/{id}",
                                "/api/enemy/all",
                                "/api/enemy/{id}",
                                "/api/enemy/health/{id}",
                                "/api/hero/{id}",
                                "/api/hero/health/{id}",
                                "/api/player-tip/all",
                                "/api/player-tip/random",
                                "/api/hero/list/{id}",
                                "/api/hero/all",
                                "/api/hero/list",
                                "/api/hero/list/high-score",
                                "/api/inventory/loot-options/{id}",
                                "/api/inventory/slots/{id}",
                                "/api/inventory/{id}",
                                "/api/inventory/potions/{id}",
                                "/api/loot/{id}"
                        ).permitAll()
                        .requestMatchers(HttpMethod.DELETE,
                                "/api/hero/delete/{id}",
                                "/delete/{id}",
                                "/api/inventory/remove/{id}"
                        ).permitAll()
                        .requestMatchers(HttpMethod.PUT,
                                "/update/{id}"
                        ).permitAll()
                        .anyRequest().authenticated());
        return http.build();
    }
}
