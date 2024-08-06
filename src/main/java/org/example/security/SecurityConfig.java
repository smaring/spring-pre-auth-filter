package org.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {


  @Bean
  public AuthenticationProvider authenticationProvider() {
    PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
    provider.setPreAuthenticatedUserDetailsService( new CustomPreAuthUserDetailsService() );
    return provider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider) {
    return new ProviderManager(authenticationProvider);
  }


  public CustomPreAuthFilter customPreAuthFilter( AuthenticationManager authenticationManager ) {
    CustomPreAuthFilter customPreAuthFilter = new CustomPreAuthFilter();
    customPreAuthFilter.setAuthenticationManager( authenticationManager );
    customPreAuthFilter.setPrincipalRequestParam( "email" );
    customPreAuthFilter.setAuthenticationDetailsSource( new CustomPreAuthenticationDetailsSource() );
    customPreAuthFilter.setAuthenticationSuccessHandler( new CustomPreAuthSuccessHandler() );
    return customPreAuthFilter;
  }


  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http,
                                          AuthenticationManager authenticationManager) throws Exception {

    return http
            .csrf(AbstractHttpConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
            .anonymous(AbstractHttpConfigurer::disable)
            .logout(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests( request ->
                    request.anyRequest().authenticated() )
            .addFilter( customPreAuthFilter( authenticationManager ) )
            .build();

  }


}
