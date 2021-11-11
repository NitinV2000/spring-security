package com.example.springsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends  WebSecurityConfigurerAdapter{

    @Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	private PasswordEncoder passwordEncoder;
	

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails nitin = User.builder()
				.username("Nitin")
				.password(passwordEncoder.encode("1234"))
				.roles(ApplicationUserRole.STUDENT.name())
				.build();
		
		UserDetails Tintin = User.builder()
				.username("Tintin")
				.password(passwordEncoder.encode("12345"))
				.roles(ApplicationUserRole.ADMIN.name())
				.build();
		
		return new InMemoryUserDetailsManager(nitin,Tintin);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/","index","/css/+","/js/+")
		.permitAll()
		.antMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name())
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
	}

}
