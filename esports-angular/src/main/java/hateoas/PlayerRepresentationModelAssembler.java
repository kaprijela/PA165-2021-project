package hateoas;

import cz.muni.fi.pa165.esports.dto.CompetitionDTO;
import cz.muni.fi.pa165.esports.dto.PlayerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import javax.inject.Inject;

@Slf4j
public class PlayerRepresentationModelAssembler implements RepresentationModelAssembler<PlayerDTO, EntityModel<PlayerDTO>> {
    @Inject
    private EntityLinks entityLinks;

    @Override
    public EntityModel<PlayerDTO> toModel(PlayerDTO entity) {
        long id = entity.getId();
        EntityModel<PlayerDTO> playerResourceModel = new EntityModel<>(entity);
        try {
            Link catLink = entityLinks.linkForItemResource(PlayerDTO.class, id).withSelfRel();
            playerResourceModel.add(catLink);

            Link playerLink = entityLinks.linkForItemResource(CompetitionDTO.class, id).slash("/competitions").withRel("competitions");
        } catch (Exception e){
            log.error("cannot link HATEOAS: {}", e.getMessage());
        }
        return playerResourceModel;
    }
}
