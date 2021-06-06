package cz.muni.fi.pa165.esports.hateoas;

import cz.muni.fi.pa165.esports.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Slf4j
@Component
public class UserRepresentationModelAssembler implements RepresentationModelAssembler<UserDTO, EntityModel<UserDTO>> {
    @Inject
    private EntityLinks entityLinks;

    @Override
    public EntityModel<UserDTO> toModel(UserDTO entity) {
        long id = entity.getId();
        EntityModel<UserDTO> userResourceModel = new EntityModel<>(entity);
        try {
            Link catLink = entityLinks.linkForItemResource(UserDTO.class, id).withSelfRel();
            userResourceModel.add(catLink);

            //Link playerLink = entityLinks.linkForItemResource(CompetitionDTO.class, id).slash("/competitions").withRel("competitions");
        } catch (Exception e) {
            log.error("cannot link HATEOAS: {}", e.getMessage());
        }
        return userResourceModel;
    }
}
