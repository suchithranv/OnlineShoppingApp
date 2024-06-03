		package com.shopping.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {
	//Use SecurityFilterChain to configure security settings
	
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		//Use BCryptPasswordEncoder for password encoding.
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	//spring security will process login and logout

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
	    http
	        .csrf(csrf -> csrf.disable())
	        .cors(cors -> cors.disable())
	        
	    //Request Authorization:
	    //Secures all endpoints under /user/** so that only users with the ROLE_USER can access them.
	    //Secures all endpoints under /admin/** so that only users with the ROLE_ADMIN can access them.
	    // Allows unrestricted access to all other endpoints.
	        .authorizeHttpRequests(req -> req
	            .requestMatchers("/user/**").hasRole("USER")
	            .requestMatchers("/admin/**").hasRole("ADMIN")
	            .requestMatchers("/**").permitAll()
	        )
	  
	     //Form-Based Login Configuration:
	     //Specifies the custom login page URL. When a user needs to log in, they will be redirected to /signin.
	    //Specifies the URL to submit the login form. Spring Security will process the login request at this URL.
        //Uses a custom AuthenticationSuccessHandler to handle successful authentication events. 
        //It is used to customize the behavior after a successful login, such as redirecting the user to a specific page or displaying a custom message.
	        .formLogin(form -> form
	            .loginPage("/signin")
	            .loginProcessingUrl("/login")
	            .successHandler(authenticationSuccessHandler)
	        )
	        
	    //Logout Configuration: 
	    //permitAll(): Allows unrestricted access to the logout functionality, enabling any user to log out.
	        .logout(logout -> logout.permitAll());
	    
	    return http.build();

/** 
The provided filterChain method sets up a basic security configuration with the following characteristics:
CSRF and CORS protections are disabled.
Access to /user/** endpoints is restricted to users with the ROLE_USER.
Access to /admin/** endpoints is restricted to users with the ROLE_ADMIN.
All other endpoints are publicly accessible.
A custom login page is specified at /signin, with login processing handled at /login.
A custom success handler is used to handle successful login events.
Logout functionality is accessible to all users.
This configuration ensures that only authorized users can access specific parts of the application while allowing public access to other areas and providing a custom login experience.
**/
	    
	    
	}


}




/**
Client Request: A user requests a protected resource, e.g., /profile.
Security Filter Chain: The request passes through the security filters.
Authentication Filter: If the user needs to log in, the UsernamePasswordAuthenticationFilter processes the login form.
AuthenticationManager: The credentials are passed to the AuthenticationManager.
AuthenticationProvider: The DaoAuthenticationProvider uses the UserDetailsService to fetch user data and 
PasswordEncoder to verify the password.
Successful Authentication: On success, an Authentication object is stored in the SecurityContextHolder.
Authorization: The AccessDecisionManager checks if the authenticated user has access to the resource.
Resource Access: If authorized, the request is processed and the resource is returned to the user.
Post-Processing: The security context is cleared, and any other necessary cleanup is performed.
**/
