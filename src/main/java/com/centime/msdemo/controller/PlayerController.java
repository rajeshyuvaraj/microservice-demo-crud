package com.centime.msdemo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centime.msdemo.model.Player;
import com.centime.msdemo.repository.PlayerRepository;

@RestController
@RequestMapping("/api")
public class PlayerController {

	@Autowired
	PlayerRepository playerRepository;

	@GetMapping("/players")
	public ResponseEntity<Map<Long, List<Player>>> getAllPlayers() {
		try {
			List<Player> players = playerRepository.findAll();
			Map<Long, List<Player>> playersMap = new HashMap<Long, List<Player>>();
			for (Player p : players) {
				if (p.getParentId() == 0) {
					playersMap.put(p.getId(), new ArrayList<>());
				}
			}
			for (Player p : players) {
				if (p.getParentId() != 0) {
					if (playersMap.get(p.getParentId()) == null) {
						playersMap.put(p.getParentId(), new ArrayList<>());
					}
					playersMap.get(p.getParentId()).add(p);
				}
			}
			return new ResponseEntity<>(playersMap, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/players/{id}")
	public ResponseEntity<Player> getPlayerId(@PathVariable("id") long id) {
		Optional<Player> playerData = playerRepository.findById(id);

		if (playerData.isPresent()) {
			return new ResponseEntity<>(playerData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/players")
	public ResponseEntity<Player> createPlayers(@RequestBody Player player) {
		try {
			Player _players = playerRepository.save(player);
			return new ResponseEntity<>(_players, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
