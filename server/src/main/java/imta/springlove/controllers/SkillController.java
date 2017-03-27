package imta.springlove.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imta.springlove.entities.Skill;
import imta.springlove.repositories.SkillRepository;

@RestController
@RequestMapping(value = "/skill")
public class SkillController {

	@GetMapping(value = "/all")
	public List<Skill> getSkill() throws SQLException {
		return SkillRepository.getSkills();
	}
	
	@PostMapping
	public ResponseEntity<?> persistSkill(@RequestBody Skill skill) throws URISyntaxException {
		try {
			System.out.println("############\n " + skill + " \n##########\n");
			SkillRepository.persist(skill);
			return ResponseEntity.created(new URI("")).build();
		} catch (SQLException exception) {
			return ResponseEntity.status(500).build();
		}
		
	}
}
