package gr.unipi.lecturer.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
      //JpaVendorAdapteradapter can be autowired as well if it's configured in application properties.
      HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
      vendorAdapter.setGenerateDdl(false);

      LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
      factory.setJpaVendorAdapter(vendorAdapter);
      //Add package to scan for entities.
      factory.setPackagesToScan("gr.unipi.lecturer.model");
      factory.setDataSource(dataSource());
      factory.setJpaProperties(hibernateProperties());
      return factory;
  }


  @Bean
  public LocalSessionFactoryBean sessionFactory() {
      LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
      sessionFactory.setDataSource(dataSource());
      sessionFactory.setPackagesToScan("gr.unipi.lecturer.model");
      sessionFactory.setHibernateProperties(hibernateProperties());

      return sessionFactory;
  }

  @Bean
  public DataSource dataSource() {
      BasicDataSource dataSource = new BasicDataSource();
      dataSource.setDriverClassName("com.mysql.jdbc.Driver");
      dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/RatingSystem");
      dataSource.setUsername("root");
      dataSource.setPassword("secret");

      return dataSource;
  }

  @Bean(name="transactionManager")
  public PlatformTransactionManager hibernateTransactionManager() {
      HibernateTransactionManager transactionManager
        = new HibernateTransactionManager();
      transactionManager.setSessionFactory(sessionFactory().getObject());
      return transactionManager;
  }

  private final Properties hibernateProperties() {
      Properties hibernateProperties = new Properties();
    
      hibernateProperties.setProperty(
        "hibernate.dialect", "org.hibernate.dialect.MariaDBDialect");
      hibernateProperties.setProperty("hibernate.show_sql", "true");
      return hibernateProperties;
  }
}
