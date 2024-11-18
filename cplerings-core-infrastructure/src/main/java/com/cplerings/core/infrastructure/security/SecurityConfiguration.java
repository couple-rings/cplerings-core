package com.cplerings.core.infrastructure.security;

import com.cplerings.core.common.api.APIConstant;
import com.cplerings.core.common.profile.ProfileConstant;
import com.cplerings.core.common.security.RoleConstant;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
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
        handleCraftingStageAPI(localHttp);
        handleDiamondSpecificationAPI(localHttp);
        handleMetalSpecificationAPI(localHttp);
        handleContractAPI(localHttp);
        handleOrderAPI(localHttp);
        handleAgreementAPI(localHttp);
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
                        .authenticated())
                .authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.GET, resolvePath(APIConstant.TRANSPORTERS_PATH))
                        .hasAnyAuthority(RoleConstant.ROLE_STAFF, RoleConstant.ROLE_MANAGER))
                .authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.GET, resolvePath(APIConstant.JEWELERS_PATH))
                        .hasAnyAuthority(RoleConstant.ROLE_STAFF, RoleConstant.ROLE_MANAGER));
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
        localHttp.authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.POST, resolvePath(APIConstant.SPOUSES_PATH))
                        .authenticated())
                .authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.GET, resolvePath(APIConstant.SPOUSES_PATH))
                        .hasAnyAuthority(RoleConstant.ROLE_STAFF, RoleConstant.ROLE_CUSTOMER, RoleConstant.ROLE_MANAGER));
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
                        .hasAnyAuthority(RoleConstant.ROLE_CUSTOMER, RoleConstant.ROLE_STAFF))
                .authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.GET, resolvePath(APIConstant.VIEW_CURRENT_DESIGN_VERSIONS_PATH))
                        .hasAuthority(RoleConstant.ROLE_CUSTOMER))
                .authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.PUT, resolvePath(APIConstant.ACCEPT_SINGLE_DESIGN_VERSION_PATH))
                        .hasAnyAuthority(RoleConstant.ROLE_CUSTOMER))
                .authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.GET, resolvePath(APIConstant.VIEW_CUSTOM_DESIGNS_PATH))
                        .hasAnyAuthority(RoleConstant.ROLE_CUSTOMER, RoleConstant.ROLE_STAFF, RoleConstant.ROLE_MANAGER))
                .authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.GET, resolvePath(APIConstant.VIEW_CUSTOM_DESIGN_PATH))
                        .hasAnyAuthority(RoleConstant.ROLE_CUSTOMER, RoleConstant.ROLE_STAFF))
                .authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.GET, resolvePath(APIConstant.VIEW_DESIGN_SESSIONS_LEFT_PATH))
                        .hasAnyAuthority(RoleConstant.ROLE_CUSTOMER, RoleConstant.ROLE_STAFF));
    }

    private void handleFileUploadAPI(HttpSecurity localHttp) throws Exception {
        localHttp.authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.POST, resolvePath(APIConstant.FILES_PATH))
                .authenticated());
    }

    private void handleCustomRequestAPI(HttpSecurity localHttp) throws Exception {
        localHttp.authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.GET, resolvePath(APIConstant.VIEW_CUSTOM_REQUESTS_PATH))
                        .permitAll())
                .authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.PUT, resolvePath(APIConstant.DETERMINE_CUSTOM_REQUEST_PATH))
                        .permitAll())
                .authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.GET, resolvePath(APIConstant.CUSTOM_SINGLE_REQUEST_PATH))
                        .hasAnyAuthority(RoleConstant.ROLE_STAFF, RoleConstant.ROLE_CUSTOMER))
                .authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.POST, resolvePath(APIConstant.CUSTOM_REQUEST_PATH))
                        .hasAnyAuthority(RoleConstant.ROLE_CUSTOMER, RoleConstant.ROLE_STAFF));
    }

    private void handleCraftingRequestAPI(HttpSecurity localHttp) throws Exception {
        localHttp.authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.POST, resolvePath(APIConstant.CRAFTING_REQUEST_PATH))
                        .hasAnyAuthority(RoleConstant.ROLE_CUSTOMER, RoleConstant.ROLE_STAFF))
                .authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.PUT, resolvePath(APIConstant.ACCEPT_CRAFTING_REQUEST_PATH))
                        .hasAnyAuthority(RoleConstant.ROLE_STAFF))
                .authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.GET, resolvePath(APIConstant.VIEW_CRAFTING_REQUESTS_PATH))
                        .hasAnyAuthority(RoleConstant.ROLE_STAFF, RoleConstant.ROLE_CUSTOMER, RoleConstant.ROLE_MANAGER))
                .authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.GET, resolvePath(APIConstant.VIEW_CRAFTING_REQUEST_GROUPS_PATH))
                        .hasAnyAuthority(RoleConstant.ROLE_STAFF, RoleConstant.ROLE_CUSTOMER, RoleConstant.ROLE_MANAGER));
    }

    private void handleCraftingStageAPI(HttpSecurity localHttp) throws Exception {
        localHttp.authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.POST, resolvePath(APIConstant.DEPOSIT_CRAFTING_STAGE_PATH))
                        .hasAnyAuthority(RoleConstant.ROLE_CUSTOMER, RoleConstant.ROLE_STAFF))
                .authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.POST, resolvePath(APIConstant.COMPLETE_CRAFTING_STAGE_PATH))
                        .hasAnyAuthority(RoleConstant.ROLE_STAFF, RoleConstant.ROLE_JEWELER))
                .authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.GET, resolvePath(APIConstant.VIEW_CRAFTING_REQUEST_PATH))
                        .hasAnyAuthority(RoleConstant.ROLE_STAFF, RoleConstant.ROLE_CUSTOMER))
                .authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.GET, resolvePath(APIConstant.CRAFTING_STAGE_PATH))
                        .hasAnyAuthority(RoleConstant.ROLE_STAFF, RoleConstant.ROLE_CUSTOMER, RoleConstant.ROLE_JEWELER, RoleConstant.ROLE_MANAGER));
    }

    private void handleDiamondSpecificationAPI(HttpSecurity localHttp) throws Exception {
        localHttp.authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.GET, resolvePath(APIConstant.DIAMOND_SPECIFICATION_PATH))
                .authenticated());
    }

    private void handleMetalSpecificationAPI(HttpSecurity localHttp) throws Exception {
        localHttp.authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.GET, resolvePath(APIConstant.METAL_SPECIFICATION_PATH))
                .permitAll());
    }

    private void handleContractAPI(HttpSecurity localHttp) throws Exception {
        localHttp.authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.PUT, resolvePath(APIConstant.SIGNING_CONTRACT_PATH))
                .hasAnyAuthority(RoleConstant.ROLE_CUSTOMER));
    }

    private void handleOrderAPI(HttpSecurity localHttp) throws Exception {
        localHttp.authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.POST, resolvePath(APIConstant.ASSIGN_TRANSPORTATION_ORDER_PATH))
                        .hasAnyAuthority(RoleConstant.ROLE_STAFF))
                .authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.PUT, resolvePath(APIConstant.UPDATE_TRANSPORTATION_ORDER_TO_ONGOING_PATH))
                        .hasAnyAuthority(RoleConstant.ROLE_TRANSPORTER))
                .authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.GET, resolvePath(APIConstant.TRANSPORTATION_ORDER_PATH))
                        .hasAnyAuthority(RoleConstant.ROLE_TRANSPORTER, RoleConstant.ROLE_STAFF, RoleConstant.ROLE_MANAGER))
                .authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.GET, resolvePath(APIConstant.CUSTOM_ORDERS_PATH))
                        .hasAnyAuthority(RoleConstant.ROLE_CUSTOMER, RoleConstant.ROLE_JEWELER, RoleConstant.ROLE_STAFF, RoleConstant.ROLE_MANAGER))
                .authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.GET, resolvePath(APIConstant.VIEW_A_CUSTOM_ORDER_PATH))
                        .hasAnyAuthority(RoleConstant.ROLE_CUSTOMER, RoleConstant.ROLE_JEWELER, RoleConstant.ROLE_STAFF, RoleConstant.ROLE_MANAGER))
                .authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.PUT, resolvePath(APIConstant.UPDATE_TRANSPORTATION_ORDER_STATUS))
                        .hasAnyAuthority(RoleConstant.ROLE_TRANSPORTER));
    }

    private void handleAgreementAPI(HttpSecurity localHttp) throws Exception {
        localHttp.authorizeHttpRequests(config -> config.requestMatchers(HttpMethod.GET, resolvePath(APIConstant.AGREEMENTS_PATH))
                .permitAll());
    }

    private String resolvePath(String path) {
        return (apiPath + path);
    }
}
