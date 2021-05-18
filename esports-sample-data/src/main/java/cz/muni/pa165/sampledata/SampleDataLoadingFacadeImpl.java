package cz.muni.pa165.sampledata;


import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.enums.Gender;
import cz.muni.fi.pa165.esports.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;


/**
 * Loads some sample data to populate the eshop database.
 *
 * @author Radovan Tomasik
 */
@Component
@Transactional //transactions are handled on facade layer
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {
    @Autowired
    PlayerService playerService;

    @Override
    public void loadData() throws IOException {
        Player player = new Player();
        player.setName("Radko");
        player.setGender(Gender.MALE);
        playerService.create(player);
    }
}
