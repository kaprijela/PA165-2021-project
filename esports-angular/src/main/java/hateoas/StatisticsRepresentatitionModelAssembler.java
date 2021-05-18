package hateoas;

import cz.muni.fi.pa165.esports.dto.CompetitionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Date;

@Slf4j
@Component
public class StatisticsRepresentatitionModelAssembler implements RepresentationModelAssembler<Double, EntityModel<Double>> {

    @Inject
    private EntityLinks entityLinks;


    @Override
    public EntityModel<Double> toModel(Double d) {
            return new EntityModel<>(d);
    }
}
