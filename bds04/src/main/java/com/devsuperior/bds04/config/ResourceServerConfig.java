package com.devsuperior.bds04.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

@Configuration
@EnableResourceServer // ResourceServer is enable to use the functionality of OAuth2
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private Environment env; // Environment of execute from application. Can access many variables
	
	@Autowired
	private JwtTokenStore tokenStore;
	
	
	// ROUTES
	
	// something/** = all after something is access
	
	private static final String[] PUBLIC = {"/oauth/token", "/h2-console/**", "/events/**"}; // route to login  / this path or everything after this path

	private static final String[] ADMIN = {"/cities/**"}; // only admin
	//private static final String[] CLIENT = {"/cities/**"};


	// private static final String[] CLIENT_AND_ADMIN = {"/events/**", "/cities/**"};

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		// Access H2
		if(Arrays.asList(env.getActiveProfiles()).contains("test")){// If actives profiles contains test profiles
			http.headers().frameOptions().disable(); // release the frames and include h2 frame
		}
		
		
		http.authorizeRequests()
		.antMatchers(PUBLIC).permitAll() // Permit all route PUBLIC dont required login (OAuth)
		.antMatchers(HttpMethod.POST, ADMIN).permitAll()// Required Login
		.antMatchers(HttpMethod.GET, ADMIN).permitAll().anyRequest().hasAnyRole("ADMIN");
		//.antMatchers(HttpMethod.GET, CLIENT).hasAnyRole("ADMIN");
		//.antMatchers(HttpMethod.GET, CLIENT_AND_ADMIN).permitAll()
		//.antMatchers(ADMIN).hasAnyRole("ADMIN"); // in the database (data.sql) requires the prefix ROLE_
	}

}
