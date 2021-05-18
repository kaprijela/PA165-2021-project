package cz.muni.fi.pa165.esports.service.config;

import cz.muni.fi.pa165.esports.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.esports.facade.CompetitionFacade;
import cz.muni.fi.pa165.esports.service.TeamService;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Service configuration class.
 * Taken from the <a href="https://github.com/fi-muni/PA165/blob/master/eshop-service/src/main/java/cz/fi/muni/pa165/service/config/ServiceConfiguration.java">reference project</a>
 *
 * @author Gabriela Kandova
 */
@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackageClasses={TeamService.class, CompetitionFacade.class})
public class ServiceConfiguration {
    @Bean
    public Mapper dozer() {
        DozerBeanMapper dozer = new DozerBeanMapper();
        return dozer;
    }
}
