package org.example.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Collection;
import java.util.List;

public class CustomPreAuthUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

  @Override
  public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
    List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("USER"));
    return this.buildUserDetails(token, authorities);
  }

  protected UserDetails buildUserDetails(PreAuthenticatedAuthenticationToken token, Collection<? extends GrantedAuthority> authorities) {
    return new CustomPreAuthUserDetails(token.getName(), authorities);
  }
}
