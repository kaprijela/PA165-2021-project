package cz.muni.fi.pa165.esports.hateoas;

import cz.muni.fi.pa165.esports.dto.CompetitionDTO;
import cz.muni.fi.pa165.esports.dto.TeamDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Slf4j
@Component
public class TeamRepresentationModelAssembler implements RepresentationModelAssembler<TeamDTO, EntityModel<TeamDTO>> {

    @Inject
    private EntityLinks entityLinks;

    @Override
    public EntityModel<TeamDTO> toModel(TeamDTO entity) {
        long id = entity.getId();
        EntityModel<TeamDTO> competitionResourceModel = new EntityModel<>(entity);
        try {
            Link catLink = entityLinks.linkForItemResource(TeamDTO.class, id).withSelfRel();
            competitionResourceModel.add(catLink);

            Link competitionLink = entityLinks.linkForItemResource(CompetitionDTO.class, id).slash("/teams").withRel("teams");
        } catch (Exception e) {
            log.error("cannot link HATEOAS: {}", e.getMessage());
        }
        return competitionResourceModel;
    }
}
