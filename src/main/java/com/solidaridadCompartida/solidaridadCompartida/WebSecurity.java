/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solidaridadCompartida.solidaridadCompartida;

import com.solidaridadCompartida.solidaridadCompartida.service.BeneficiaryService;
import com.solidaridadCompartida.solidaridadCompartida.service.DonorService;
import com.solidaridadCompartida.solidaridadCompartida.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true )
public class WebSecurity extends WebSecurityConfigurerAdapter {
    
   @Autowired 
   public BeneficiaryService beneficiaryservice; 
   
   @Autowired
   public DonorService donorservice;
   
   @Autowired
   public PersonService personservice;
   
    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception{
        
     auth.userDetailsService(beneficiaryservice)
     .passwordEncoder(new BCryptPasswordEncoder() );
        
    } */
    
    /*@Autowired 
    public void configureGlobal2(AuthenticationManagerBuilder auth) throws Exception{
    
     auth.userDetailsService(donorservice)
     .passwordEncoder(new BCryptPasswordEncoder() );
        
    } */
    
      @Autowired 
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
    
     auth.userDetailsService(personservice)
     .passwordEncoder(new BCryptPasswordEncoder() );
        
    }
    
    
    @Override   
    protected void configure(HttpSecurity http)throws Exception {
 
    http 
            .authorizeRequests()
            .antMatchers("/css/*","/js/*","img/*","/**")
            .permitAll()
    .and().formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login/form")
            .usernameParameter("email")
            .passwordParameter("password")
            .defaultSuccessUrl("/login/default")//aca va hacia la nueva vista en donde se dirije el usuario luego de logearse
            .failureUrl("/login?error=true") 
            .permitAll()
            
    .and().logout()
            .logoutUrl("/logout")//salir de la cuenta
            .logoutSuccessUrl("/") //si se cierra bien la sesion
            .permitAll()
    .and().csrf()
            .disable();
            
           
            
 
 
 
 } 
    
    
}
