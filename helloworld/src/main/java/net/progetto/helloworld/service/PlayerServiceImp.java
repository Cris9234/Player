package net.progetto.helloworld.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import net.progetto.helloworld.models.Player;
import net.progetto.helloworld.repository.PlayerRepository;

@Service
public class PlayerServiceImp implements PlayerService{
	
	@Autowired
	private PlayerRepository playerRepository;

	@Override
	public List<Player> getAllPlayers() {
		return playerRepository.findAll();
	}

	@Override
	public Player getPlayer(Long id) {
		Optional<Player> player = this.playerRepository.findById(id);
		if (player.isPresent()) {
			return player.get();
		}
		else {
			return null;
		}
	}

	@Override
	public void addPlayer(Player player) {
		playerRepository.save(player);
	}
	
	

	@Override
	public void updatePlayer(Long id, Player player) {
		try {
			Optional<Player> playerToUpdate = this.playerRepository.findById(id);
			if (playerToUpdate.isPresent()) {
				playerToUpdate.get().setFirstName(player.getFirstName());
				playerToUpdate.get().setLastName(player.getLastName());
				playerToUpdate.get().setPosition(player.getPosition());
				playerToUpdate.get().setTeam(player.getTeam());
				playerRepository.save(null);
			}
			
		} catch (Exception e) {
			throw new ServiceException("execution failed");
		}
	}

//	@Override
//	public void deletePlayer(Long id) {
//		playerRepository.deleteById(id);
//	}
	@Override
	public void deletePlayer(Long id) {
		Optional<Player> player = this.playerRepository.findById(id);
		if (player.isPresent()) {
			playerRepository.deleteById(player.get().getId());
		}
	}

}
