package es.bsc.demiurge.ws;

import es.bsc.demiurge.core.configuration.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.ProviderManagerBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.context.support.StaticWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;

/**
 * @author Mario Macías (http://github.com/mariomac)
 */
@Configuration
@ComponentScan(basePackages = "es.bsc.demiurge.ws")
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("app/**").addResourceLocations("classpath:/static/app/");
		registry.addResourceHandler("css/**").addResourceLocations("classpath:/static/css/");
		registry.addResourceHandler("images/**").addResourceLocations("classpath:/static/images/");
		registry.addResourceHandler("scripts/**").addResourceLocations("classpath:/static/scripts/");
    }

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		super.configureViewResolvers(registry);
		registry.velocity()
				.prefix("/views/")
				.suffix(".vm");
	}

	@Bean
	public VelocityConfigurer getVelocityConfigurer() {
		VelocityConfigurer velocityConfigurer =
				new  VelocityConfigurer();
		return velocityConfigurer;
	}
}
