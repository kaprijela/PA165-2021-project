package cz.muni.fi.pa165.esports.service.facade;

import cz.muni.fi.pa165.esports.dto.PlayerDTO;
import cz.muni.fi.pa165.esports.facade.PlayerFacade;
import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.service.BeanMappingService;
import cz.muni.fi.pa165.esports.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Radovan Tomasik
 */
@Service
@Transactional
public class PlayerFacadeImpl implements PlayerFacade {

    @Inject
    private PlayerService playerService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public PlayerDTO findPlayerById(Long id) {
        Player player = playerService.findById(id);
        return (player == null) ? null : beanMappingService.mapTo(player, PlayerDTO.class);
    }

    @Override
    public List<PlayerDTO> findPlayerByName(String name) {
        List<Player> players = playerService.findByName(name);
        return (players.isEmpty()) ? null : beanMappingService.mapTo(players, PlayerDTO.class);
    }

    @Override
    public Long createPlayer(PlayerDTO playerDTO) {
        Player player = beanMappingService.mapTo(playerDTO, Player.class);
        playerService.create(player);
        playerDTO.setId(player.getId());

        return player.getId();
    }

    @Override
    public List<PlayerDTO> getAllPlayers() {
        List<PlayerDTO> list = new ArrayList<>();
        for (Player player: playerService.getAllPlayers()
             ) {
            PlayerDTO playerDTO = beanMappingService.mapTo(player, PlayerDTO.class);
            if (player.getTeam() != null) {
                playerDTO.setTeam(player.getTeam().getName());
            }
            list.add(playerDTO);
        }
        return list;
    }
}
