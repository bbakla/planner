package planner.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

public class AppInitializer extends AbstractDispatcherServletInitializer {

	@Override
	protected WebApplicationContext createServletApplicationContext() {
		AnnotationConfigWebApplicationContext wac = new AnnotationConfigWebApplicationContext();
		wac.register(WebAppConfig.class);
		return wac;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/planner/*" };
	}

	@Override
	protected WebApplicationContext createRootApplicationContext() {
		return null;
	}
 
}
