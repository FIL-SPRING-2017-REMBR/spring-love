package imta.springlove.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import imta.springlove.entities.Appetence;
import imta.springlove.entities.Experience;
import imta.springlove.entities.Skill;
import imta.springlove.entities.Worker;
import imta.springlove.repositories.WorkerRepository;

/**
 * Controller for the worker part of the API.
 */
@RestController
public class WorkerController {

	/**
	 * Persists a Worker entity with the given data
	 * @return the right HTTP status code
	 * @throws URISyntaxException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/worker")
	public ResponseEntity<?> persistWorker(
			@RequestParam(value = "fistName", required = true) String firstName,
			@RequestParam(value = "lastName", required = true) String lastName,
			@RequestParam(value = "resources", required = true) Map<String, String> resources,
			@RequestParam(value = "experiences", required = true) List<Experience> experiences,
			@RequestParam(value = "skill", required = true) Map<Skill, Appetence> skills) throws URISyntaxException {
		// instantiate the worker
		Worker worker = new Worker(firstName, lastName, resources, experiences, skills);
		worker.setId(0);
		
		// insert it in the database
		WorkerRepository.persist(worker);
		
		return ResponseEntity.created(new URI("")).build();
	}
	
	
	// TODO update method. Needs the name of the Worker and the data to update 
	
	// TODO get all workers
	
}
