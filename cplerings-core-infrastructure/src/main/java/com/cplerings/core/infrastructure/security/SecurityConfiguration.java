package com.cplerings.core.infrastructure.security;

import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.common.profile.ProfileConstant;
import com.cplerings.core.common.security.RoleConstant;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    private final AccessDeniedHandler accessDeniedHandler;

    @Value("${cplerings.api.path}")
    private String apiPath;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Value("${cplerings.allowed-origins}")
    private String[] allowedOrigins;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        HttpSecurity localHttp = http.csrf(AbstractHttpConfigurer::disable)
                .cors(config -> config.configurationSource(corsConfigurationSource()))
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .exceptionHandling(config -> config.accessDeniedHandler(accessDeniedHandler))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        handleDocsAPI(localHttp);
        handleAuthAPI(localHttp);
        handleAccountAPI(localHttp);
        handleDevelopmentAPI(localHttp);
        handleTestAPI(localHttp);
        handleVerificationAPI(localHttp);
        handleSpouseAPI(localHttp);
        handlePaymentAPI(localHttp);
        handleDesignAPI(localHttp);
        handleFileUploadAPI(localHttp);
        handleCustomRequestAPI(localHttp);
        handleCraftingRequestAPI(localHttp);
        localHttp.authorizeHttpRequests(config -> config.requestMatchers(resolvePath("/**"))
                .denyAll());
        return localHttp.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of(allowedOrigins));
        corsConfiguration.setAllowedMethods(List.of("*"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowCredentials(true);

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
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
        localHttp.authorizeHttpRequests(config -> config.requestMatchers(resolvePath(APIConstant.ACCOUNT_PATH))
                        .hasAnyAuthority(RoleConstant.ROLE_STAFF, RoleConstant.ROLE_MANAGER, RoleConstant.ROLE_ADMIN))
                .authorizeHttpRequests(config -> config.requestMatchers(resolvePath(APIConstant.REGISTER_CUSTOMER_PATH))
                        .permitAll())
                .authorizeHttpRequests(config -> config.requestMatchers(resolvePath(APIConstant.VERIFY_CUSTOMER_PATH))
                        .permitAll())
                .authorizeHttpRequests(config -> config.requestMatchers(resolvePath(APIConstant.REQUEST_RESET_PASSWORD_PATH))
                        .permitAll())
                .authorizeHttpRequests(config -> config.requestMatchers(resolvePath(APIConstant.RESET_PASSWORD_PATH))
                        .permitAll())
                .authorizeHttpRequests(config -> config.requestMatchers(resolvePath(APIConstant.CURRENT_PROFILE_PATH))
                        .authenticated());
    }

    private void handleDevelopmentAPI(HttpSecurity localHttp) throws Exception {
        if (ProfileConstant.DEVELOPMENT.equals(activeProfile)) {
            localHttp.authorizeHttpRequests(config -> config.requestMatchers(resolvePath("/dev/**"))
                    .permitAll());
        }
    }

    private void handleTestAPI(HttpSecurity localHttp) throws Exception {
        if (ProfileConstant.TEST.equals(activeProfile)) {
            localHttp.authorizeHttpRequests(config -> config.requestMatchers(resolvePath("/test/hello"))
                            .hasAnyAuthority(RoleConstant.ROLE_CUSTOMER, RoleConstant.ROLE_MANAGER))
                    .authorizeHttpRequests(config -> config.requestMatchers(resolvePath("/test/**"))
                            .permitAll());
        }
    }

    private void handleVerificationAPI(HttpSecurity localHttp) throws Exception {
        localHttp.authorizeHttpRequests(config -> config.requestMatchers(resolvePath(APIConstant.RESEND_CUSTOMER_VERIFICATION_CODE_PATH))
                .permitAll());
    }

    private void handleSpouseAPI(HttpSecurity localHttp) throws Exception {
        localHttp.authorizeHttpRequests(config -> config.requestMatchers(resolvePath(APIConstant.SPOUSES_PATH))
                .authenticated());
    }

    private void handlePaymentAPI(HttpSecurity localHttp) throws Exception {
        localHttp.authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.GET, resolvePath(APIConstant.VNPAY_PATH))
                .permitAll());
    }

    private void handleDesignAPI(HttpSecurity localHttp) throws Exception {
        localHttp.authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.GET, resolvePath(APIConstant.DESIGN_COUPLE_PATH))
                        .permitAll())
                .authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.GET, resolvePath(APIConstant.VIEW_SINGLE_DESIGN_VERSION_PATH))
                        .permitAll())
                .authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.POST, resolvePath(APIConstant.CREATE_DESIGN_SESSION_PATH))
                        .hasAuthority(RoleConstant.ROLE_CUSTOMER))
                .authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.GET, resolvePath(APIConstant.DESIGN_SESSION_PATH))
                        .hasAuthority(RoleConstant.ROLE_CUSTOMER))
                .authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.POST, resolvePath(APIConstant.DESIGN_VERSION_PATH))
                        .hasAuthority(RoleConstant.ROLE_STAFF))
                .authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.POST, resolvePath(APIConstant.CREATE_CUSTOM_DESIGN_PATH))
                        .hasAnyAuthority(RoleConstant.ROLE_STAFF))
                .authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.GET, resolvePath(APIConstant.DESIGN_VERSION_PATH))
                        .hasAuthority(RoleConstant.ROLE_CUSTOMER))
                .authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.GET, resolvePath(APIConstant.VIEW_CURRENT_DESIGN_VERSIONS_PATH))
                        .hasAuthority(RoleConstant.ROLE_CUSTOMER));
    }

    private void handleFileUploadAPI(HttpSecurity localHttp) throws Exception {
        localHttp.authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.POST, resolvePath(APIConstant.FILES_PATH))
                .authenticated());
    }

    private void handleCustomRequestAPI(HttpSecurity localHttp) throws Exception {
        localHttp.authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.GET, resolvePath(APIConstant.CUSTOM_SINGLE_REQUEST_PATH))
                        .hasAnyAuthority(RoleConstant.ROLE_STAFF))
                .authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.POST, resolvePath(APIConstant.CUSTOM_REQUEST_PATH))
                        .hasAnyAuthority(RoleConstant.ROLE_CUSTOMER, RoleConstant.ROLE_STAFF));
    }

    private void handleCraftingRequestAPI(HttpSecurity localHttp) throws Exception {
        localHttp.authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.POST, resolvePath(APIConstant.CRAFTING_REQUEST_PATH))
                        .hasAnyAuthority(RoleConstant.ROLE_CUSTOMER, RoleConstant.ROLE_STAFF))
                .authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.PUT, resolvePath(APIConstant.ACCEPT_CRAFTING_REQUEST_PATH))
                        .hasAnyAuthority(RoleConstant.ROLE_STAFF));
    }

    private String resolvePath(String path) {
        return (apiPath + path);
    }
}
