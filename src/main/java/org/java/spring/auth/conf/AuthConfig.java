package org.java.spring.auth.conf;

import org.java.spring.auth.services.UserServ;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthConfig {
	@Bean
	PasswordEncoder passwordEncoder() {
		
//	    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		return new BCryptPasswordEncoder();
	}
	
	@SuppressWarnings("deprecation")
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		return http.authorizeRequests(a->
//				a.requestMatchers("/**").hasAnyAuthority("USER" , "ADMIN")
//				.requestMatchers("/admin/**").hasAuthority("ADMIN")
//				.requestMatchers("/**").permitAll()
//				)
//				.formLogin(f->f.permitAll())
//				.logout(l->l.logoutSuccessUrl("/"))
//				.build();
		
		String[] adminMatchers = {
				"/pizzas/create" , "/pizzas/edit/**" , "/pizzas/trash" ,"/pizzas/soft-delete/**" , "/pizzas/soft-delete-all" ,
				"/pizzas/refresh/**" , "/pizzas/refresh-all" , "/pizzas/delete/**" , "/pizzas/delete-all",

				"/special-offers/create" , "/special-offers/edit/**" , "/special-offers/trash" ,"/special-offers/soft-delete/**" , "/special-offers/soft-delete-all" ,
				"/special-offers/refresh/**" , "/special-offers/refresh-all" , "/special-offers/delete/**" , "/special-offers/delete-all",

				"/ingredients/create" , "/ingredients/edit/**" , "/ingredients/trash" ,"/ingredients/soft-delete/**" , "/ingredients/soft-delete-all" ,
				"/ingredients/refresh/**" , "/ingredients/refresh-all" , "/ingredients/delete/**" , "/ingredients/delete-all",
				};
		String[] userMatchers = {"/pizzas/" , "/pizzas/show/**" , "/ingredients/" , "/special-offers/" , "/special-offers/show/**"};
		
		return http.authorizeRequests(a->a
				.requestMatchers(userMatchers).hasAnyAuthority("USER" , "ADMIN")
				.requestMatchers(HttpMethod.POST , "/pizzas/").hasAnyAuthority("USER" , "ADMIN")
				.requestMatchers(adminMatchers).hasAnyAuthority("ADMIN")
//				.requestMatchers("").hasAuthority("USER")
				.requestMatchers("/").permitAll()
				)
				.formLogin(f->f.permitAll())
				.logout(l->l.logoutSuccessUrl("/"))
				.build();
	}
	
	@Bean
	UserDetailsService userDetailsService() {
	    return new UserServ();
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	 
	    authProvider.setUserDetailsService(userDetailsService());
	    authProvider.setPasswordEncoder(passwordEncoder());
	 
	    return authProvider;
	}
}
