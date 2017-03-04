package br.com.casadocodigo.seguranca.basic;

import br.com.casadocodigo.seguranca.DadosDoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class ConfiguracaoDeSeguranca extends WebSecurityConfigurerAdapter  {

	@Autowired
	private DadosDoUsuarioService userAuthenticationService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userAuthenticationService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.authorizeRequests()
				.antMatchers("/usuarios").permitAll()
				.anyRequest().authenticated().and()
			.httpBasic()
				.realmName("bookserver").and()
			.csrf().disable();
		// @formatter:on
	}
	
}
