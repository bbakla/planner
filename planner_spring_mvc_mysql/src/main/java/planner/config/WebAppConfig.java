package planner.config;

import java.util.Arrays;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
@EnableTransactionManagement
@EnableWebMvc
@ComponentScan(basePackages = "planner")
public class WebAppConfig {
	
//	@Bean
//	public ContentNegotiatingViewResolver contentViewResolver() {
//	    ContentNegotiationManagerFactoryBean contentNegotiationManager = new ContentNegotiationManagerFactoryBean();
//	    contentNegotiationManager.addMediaType("json", MediaType.APPLICATION_JSON);
//
//	    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//	    viewResolver.setViewClass(JstlView.class);
//        viewResolver.setPrefix("/views/");
//        viewResolver.setSuffix(".jsp");
//
//	    MappingJackson2JsonView defaultView = new MappingJackson2JsonView();
//	    defaultView.setExtractValueFromSingleKeyModel(true);
//
//	    ContentNegotiatingViewResolver contentViewResolver = new ContentNegotiatingViewResolver();
//	    contentViewResolver.setContentNegotiationManager(contentNegotiationManager.getObject());
//	    contentViewResolver.setViewResolvers(Arrays.<ViewResolver> asList(viewResolver));
//	    contentViewResolver.setDefaultViews(Arrays.<View> asList(defaultView));
//	    return contentViewResolver;
//	}
	
	@Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
//        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setPrefix("/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
     
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

}

