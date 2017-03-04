package br.com.casadocodigo.seguranca.oauth;

import br.com.casadocodigo.seguranca.conditions.OAuthHabilitado;
import br.com.casadocodigo.seguranca.conditions.OAuthProviderSeparado;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
@Conditional({
    OAuthHabilitado.class,
    OAuthProviderSeparado.class
})
public class OAuth2ResourceServer extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("bookserver");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .requestMatchers()
                .antMatchers("/api/livros/**").and()
            .authorizeRequests()
                .antMatchers("/api/livros/**").authenticated();
        // @formatter:on
    }

}
