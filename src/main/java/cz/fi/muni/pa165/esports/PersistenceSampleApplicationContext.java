package cz.fi.muni.pa165.esports;


import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "cz.fi.muni.pa165.esports.dao")
public class PersistenceSampleApplicationContext {

    /**
     * Starts up a container that emulates behavior prescribed in JPA spec for container-managed EntityManager
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean jpaFactoryBean = new LocalContainerEntityManagerFactoryBean();
        jpaFactoryBean.setDataSource(db());
        return jpaFactoryBean;
    }

    @Bean
    public DataSource db() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.DERBY).build();
    }

}
