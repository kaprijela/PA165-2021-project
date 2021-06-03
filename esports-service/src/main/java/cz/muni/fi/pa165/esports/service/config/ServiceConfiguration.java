package cz.muni.fi.pa165.esports.service.config;

import cz.muni.fi.pa165.esports.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.esports.dto.PlayerDTO;
import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.service.dozer.TeamStringConverter;
import lombok.Builder;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Service configuration class.
 * Adapted from the <a href="https://git.io/JGrFm">reference project</a>
 *
 * @author Gabriela Kandova
 */
@Configuration
@Import(PersistenceSampleApplicationContext.class)
@Component
@ComponentScan(basePackages = "cz.muni.fi.pa165.esports.service")
public class ServiceConfiguration {
    @Bean
    public Mapper dozer() {
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.addMapping(new DozerCustomConfig());
        return dozer;
    }

    @Builder
    private static class DozerCustomConfig extends BeanMappingBuilder {
        @Override
        protected void configure() {
            mapping(Player.class, PlayerDTO.class)
                    .fields("team", "team", FieldsMappingOptions.customConverter(TeamStringConverter.class));
        }
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
