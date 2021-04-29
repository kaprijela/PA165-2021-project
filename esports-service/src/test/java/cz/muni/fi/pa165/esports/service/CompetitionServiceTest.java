package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.service.config.ServiceConfiguration;
import org.hibernate.validator.constraints.ModCheck;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * @author gavlijan
 *
 */
@ContextConfiguration(classes = {ServiceConfiguration.class})
public class CompetitionServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    CompetitionService competitionService;

}