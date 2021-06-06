package cz.muni.fi.pa165.esports.hateoas;

import cz.muni.fi.pa165.esports.dto.CompetitionDTO;
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

            Link create = entityLinks.linkFor(CompetitionDTO.class).slash("/create").withRel("competitions");
            competitionResourceModel.add(create);

            Link findByName = entityLinks.linkFor(CompetitionDTO.class).slash("/name/").withRel("findByName");
            competitionResourceModel.add(findByName);

            Link findById = entityLinks.linkFor(CompetitionDTO.class).slash("/id/").withRel("findById");
            competitionResourceModel.add(findById);

            Link delete = entityLinks.linkFor(CompetitionDTO.class).slash("/").withRel("deleteById");
            competitionResourceModel.add(delete);

            Link addTeam = entityLinks.linkForItemResource(CompetitionDTO.class, id).slash("/addTeam/").withRel("addTeamByName");
            competitionResourceModel.add(addTeam);

            Link removeTeam = entityLinks.linkForItemResource(CompetitionDTO.class, id).slash("/removeTeam/").withRel("removeTeamByName");
            competitionResourceModel.add(removeTeam);

        } catch (Exception e) {
            log.error("cannot link HATEOAS: {}", e.getMessage());
        }
        return competitionResourceModel;
    }
}
