package net.progetto.helloworld.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.progetto.helloworld.models.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>{

}
