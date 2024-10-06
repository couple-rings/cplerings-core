package com.cplerings.core.infrastructure.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cplerings.core.common.profile.ProfileConstant;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true
)
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    private final AccessDeniedHandler accessDeniedHandler;

    @Value("${cplerings.api.path}")
    private String apiPath;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        HttpSecurity localHttp = http.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .exceptionHandling(config -> config.accessDeniedHandler(accessDeniedHandler))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        handleDocsAPI(localHttp);
        handleAuthAPI(localHttp);
        handleAccountAPI(localHttp);
        if (ProfileConstant.DEVELOPMENT.equals(activeProfile)) {
            localHttp.authorizeHttpRequests(config -> config.requestMatchers(resolvePath("/dev/**"))
                    .permitAll());
        }
        if (ProfileConstant.TEST.equals(activeProfile)) {
            localHttp.authorizeHttpRequests(config -> config.requestMatchers(resolvePath("/test/**"))
                    .permitAll());
        }
        localHttp.authorizeHttpRequests(config -> config.requestMatchers(resolvePath("/**"))
                .denyAll());
        return localHttp.build();
    }

    private void handleDocsAPI(HttpSecurity localHttp) throws Exception {
        localHttp.authorizeHttpRequests(config -> config.requestMatchers(resolvePath("/docs/**"))
                .permitAll());
    }

    private void handleAuthAPI(HttpSecurity localHttp) throws Exception {
        localHttp.authorizeHttpRequests(config -> config.requestMatchers(resolvePath("/auth/**"))
                .permitAll());
    }

    private void handleAccountAPI(HttpSecurity localHttp) throws Exception {
        localHttp.authorizeHttpRequests(config -> config.requestMatchers(resolvePath("/accounts/customer/register"))
                .permitAll());
    }

    private String resolvePath(String path) {
        return (apiPath + path);
    }
}
