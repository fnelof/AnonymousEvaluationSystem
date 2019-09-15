
package gr.unipi.issue.config;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MvcWebApplicationInitializer 
      extends AbstractAnnotationConfigDispatcherServletInitializer {

  // Load all configurations
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[] { WebConfig.class, HibernateConfig.class, WebSecurityConfig.class };
  
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[0];
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] { "/" };
  }

}