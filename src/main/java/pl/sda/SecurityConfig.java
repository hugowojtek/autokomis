package pl.sda;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("123").roles("ADMIN").and()
                .withUser("hugo").password("456").roles("SALES");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/cars").permitAll()
               // .antMatchers("/login").anonymous()
                .antMatchers("/cars/*").hasRole("SALES")
                .antMatchers("/edit").hasRole("SALES")
                .antMatchers("/raports").hasRole("SALES")
                .antMatchers("/cars/*").hasRole("ADMIN")
                .antMatchers("/edit").hasRole("ADMIN")
                .antMatchers("/raports").hasRole("ADMIN")
                .antMatchers("/sale/*").hasRole("ADMIN")
                .antMatchers("/purchase/*").hasRole("ADMIN")
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/cars")
                .and().logout().logoutSuccessUrl("/cars");
    }
}
