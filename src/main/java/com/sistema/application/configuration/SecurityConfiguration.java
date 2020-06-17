package com.sistema.application.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sistema.application.services.implementations.UserService;
import com.sistema.application.helpers.ViewRouteHelper;
import com.sistema.application.helpers.UtilHelper;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    @Qualifier("userService")
    private UserService userService;

    String[] resources = new String[]{
        "/css/**", "/icons/**", "/images/**", "/js/**"
    };
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Autowired
    private CustomLogoutHandler logoutHandler; 

	@Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests()
            .antMatchers(resources).permitAll()
            .antMatchers(ViewRouteHelper.HOME_ROOT).permitAll()
            .antMatchers(HttpMethod.GET, "/empleado/**").not().hasAuthority(UtilHelper.ROLE_EMPLEADO)
            .antMatchers(HttpMethod.GET, "/producto/**").not().hasAuthority(UtilHelper.ROLE_EMPLEADO)
            .antMatchers(HttpMethod.GET, "/sueldos/**").not().hasAuthority(UtilHelper.ROLE_EMPLEADO)
            .antMatchers(HttpMethod.GET, "/ranking/**").not().hasAuthority(UtilHelper.ROLE_EMPLEADO)
            .antMatchers(HttpMethod.GET, "/distancia/**").not().hasAuthority(UtilHelper.ROLE_ADMIN)
            .antMatchers(HttpMethod.GET, "/pedido/**").not().hasAuthority(UtilHelper.ROLE_ADMIN)
            .antMatchers(HttpMethod.GET, "/factura/todas**").not().hasAuthority(UtilHelper.ROLE_ADMIN)
            .antMatchers(HttpMethod.GET, "/chango/**").not().hasAuthority(UtilHelper.ROLE_ADMIN)
            .antMatchers(HttpMethod.GET, "/notificacion/**").not().hasAuthority(UtilHelper.ROLE_ADMIN)
            .antMatchers(HttpMethod.GET, "/local/**").hasAuthority(UtilHelper.ROLE_ADMIN)
            .antMatchers(HttpMethod.GET, "/lote/**").hasAuthority(UtilHelper.ROLE_GERENTE)
            .antMatchers(HttpMethod.GET, "/reporte/**").hasAuthority(UtilHelper.ROLE_GERENTE)
            .antMatchers(HttpMethod.GET, "/items/**").hasAuthority(UtilHelper.ROLE_GERENTE)
            .antMatchers(HttpMethod.GET, "/chango/todos**").hasAuthority(UtilHelper.ROLE_GERENTE)
            .anyRequest().authenticated()
        .and()
            .formLogin().loginPage("/login").loginProcessingUrl("/loginprocess")
            .failureUrl("/login?error=true")
            .usernameParameter("username").passwordParameter("password")
            .defaultSuccessUrl("/loginsuccess").permitAll()
        .and()
            .logout()
            .invalidateHttpSession(false)  //Evita cerrar la sesión para ejecutar el handler, ahí la cierra 
            .logoutUrl("/logout")
            .addLogoutHandler(logoutHandler)
            .logoutSuccessUrl("/login?logout").permitAll()
        .and().csrf().disable();
    }
}
