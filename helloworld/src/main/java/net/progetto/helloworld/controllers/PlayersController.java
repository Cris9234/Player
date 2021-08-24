package net.progetto.helloworld.controllers;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import net.progetto.helloworld.models.Player;
import net.progetto.helloworld.service.PlayerServiceImp;

@RestController
public class PlayersController {
	
	@Autowired
	private PlayerServiceImp playerService;
	
	
	@GetMapping("/players")
	public Stream<Player> getAllPlayers() {
		return playerService.getAllPlayers().stream().sorted(Comparator.comparingLong(p -> p.getId()));
	}
	
	@GetMapping("/players/{id}")
	public ResponseEntity<Player> getPlayer(@PathVariable Long id) {
		Optional<Player> player = Optional.of(playerService.getPlayer(id));
		if ( player.isPresent()) {
			return new ResponseEntity<Player>(player.get(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Player>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/players")
	public void addPlayer(@RequestBody Player player) {
		playerService.addPlayer(player);
	}
	
	
	@PutMapping("/players/{id}")
	public void updatePlayer(@PathVariable Long id, @RequestBody Player player) {
		try {
			playerService.updatePlayer(id, player);
		} catch (Exception e) {
			throw new WebServerException("The player has not been updated", e);
		}
	}
	
//	@DeleteMapping("/players/{id}")
//	public void deletePlayer(@PathVariable Long id) {
//		playerService.deletePlayer(id);
//	}
	
	@DeleteMapping("/players/{id}")
	public void deletePlayer(@PathVariable Long id) {
		Optional<Player> player = Optional.of(playerService.getPlayer(id));
		if (player.isPresent()) {
			playerService.deletePlayer(id);
		}
		
	}
}
