
package gr.unipi.lecturer.config;

import gr.unipi.lecturer.interceptor.LoggerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
@EnableJpaRepositories(basePackages="gr.unipi.lecturer.repository")
@ComponentScan(basePackages = { "gr.unipi.lecturer.controller",
		"gr.unipi.lecturer.service",
        "gr.unipi.lecturer.interceptor"
})
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

  @Override
  public void configureViewResolvers(ViewResolverRegistry registry) {
    registry.jsp().prefix("/WEB-INF/views/").suffix(".jsp");
  }
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/login").setViewName("login");
  }
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoggerInterceptor());
  }

  @Bean
  public LoggerInterceptor requestInterceptor() {
      return new LoggerInterceptor();
  }

}
