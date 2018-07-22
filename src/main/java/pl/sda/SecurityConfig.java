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
                // niech adres "/" będzie dostepny dla kazdego
                .antMatchers("/cars").permitAll()
                // niech adres "/hi" będzie dostepny dla kazdego
                .antMatchers("/vehicles").permitAll()
                // niech adres "/login" będzie dostepny tylko dla uzytkownikow, którzy nie sa jeszcze zalogowani
                .antMatchers("/login").anonymous()
                // niech wszystkie podstrony "/vehicles" będzie dostepny tylko dla uzytkownikow, którzy posiadaja role VEHICLES
                .antMatchers("/cars/*").hasRole("SALES")
                .antMatchers("/edit/*").hasRole("SALES")
                .antMatchers("/raports/*").hasRole("ADMIN")
                .antMatchers("/sale/*").hasRole("ADMIN")
                // dowolny inny request bedzie obsluzony, jezeli uzytkownik posiada jedna z rol VEHICLES lub SALES
                .anyRequest().hasAnyRole("VEHICLES", "SALES")
                .and()
                // formularz logowania bedzie znajdowal sie pod adresem "login". Po zalogowaniu, uzytkownik bedzie przekierowany
                // na adres "/vehicles". Wazne! na adres /login musi byc wyslany POST!
                .formLogin().loginPage("/login").defaultSuccessUrl("/cars")
                // wylogowanie przekieruje na strone vehicles.
                .and().logout().logoutSuccessUrl("/cars");
    }
}
