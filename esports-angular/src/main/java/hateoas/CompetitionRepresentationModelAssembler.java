package hateoas;

import cz.muni.fi.pa165.esports.dto.CompetitionDTO;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import javax.inject.Inject;


@Slf4j
@Component
public class CompetitionRepresentationModelAssembler implements RepresentationModelAssembler<CompetitionDTO, EntityModel<CompetitionDTO>> {

    @Inject
    private EntityLinks entityLinks;

    @Override
    public EntityModel<CompetitionDTO> toModel(CompetitionDTO entity) {
        long id = entity.getId();
        EntityModel<CompetitionDTO> competitionResourceModel = new EntityModel<>(entity);
        try {
            Link catLink = entityLinks.linkForItemResource(CompetitionDTO.class, id).withSelfRel();
            competitionResourceModel.add(catLink);

            Link competitionLink = entityLinks.linkForItemResource(CompetitionDTO.class, id).slash("/competitions").withRel("competitions");
        } catch (Exception e){
            log.error("cannot link HATEOAS: {}", e.getMessage());
        }
        return competitionResourceModel;
    }
}
