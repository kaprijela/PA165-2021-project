package cz.muni.fi.pa165.esports.service.facade;

import cz.muni.fi.pa165.esports.dto.PlayerDTO;
import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.service.BeanMappingService;
import cz.muni.fi.pa165.esports.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Simple tests for the Player facade.
 *
 * @author Gabriela Kandova
 */
public class PlayerFacadeTest {
    @Mock
    PlayerService playerService;

    @Mock
    BeanMappingService bms;

    @InjectMocks
    PlayerFacadeImpl playerFacade;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Verify that the facade delegates to PlayerService::getAllPlayers.
     */
    @Test
    public void testGetAllPlayers() {
        playerService.getAllPlayers();
        Mockito.verify(playerService, Mockito.times(1)).getAllPlayers();
    }

    /**
     * Verify that the facade delegates to PlayerService::findById.
     */
    @Test
    public void testFindPlayerById() {
        playerFacade.findPlayerById(1L);
        Mockito.verify(playerService, Mockito.times(1)).findById(1L);
    }

    /**
     * Verify that the facade delegates to PlayerService::findByName.
     */
    @Test
    public void testFindPlayerByName() {
        playerFacade.findPlayerByName("Alice");
        Mockito.verify(playerService, Mockito.times(1)).findByName("Alice");
    }

    /**
     * Verify that the facade delegates to the bean
     * mapping service and PlayerService::create.
     */
    @Test
    public void testCreatePlayer() {
        PlayerDTO aliceDTO = new PlayerDTO();
        aliceDTO.setName("Alice");

        Player alice = new Player();
        alice.setName("Alice");

        Mockito.when(bms.mapTo(aliceDTO, Player.class)).thenReturn(alice);

        playerFacade.createPlayer(aliceDTO);

        Mockito.verify(bms, Mockito.times(1)).mapTo(aliceDTO, Player.class);
        Mockito.verify(playerService, Mockito.times(1)).create(alice);
    }
}