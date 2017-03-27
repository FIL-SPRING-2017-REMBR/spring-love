package imta.springlove.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
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
	@RequestMapping(method = RequestMethod.POST, value = "/worker")
	public ResponseEntity<?> persistWorker(@RequestParam(value = "worker", required = true) Worker worker) throws URISyntaxException {
		// insert it in the database
		try {
			WorkerRepository.persist(worker);
			return ResponseEntity.created(new URI("")).build();
		} catch (SQLException e) {
			// if there is a problem : return a server error
			return ResponseEntity.status(500).build();
		}
	}
	
	/**
	 * Get the workers.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/worker/all")
	public List<Worker> getWorkers() throws SQLException {
		return WorkerRepository.getWorkers();
	}
	
	/**
	 * Get a Worker by it's name.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/worker")
	public Worker getByName(
			@RequestParam(value = "firstName", required = true) String firstName,
			@RequestParam(value = "lastName", required = true) String lastName) throws SQLException {
		return WorkerRepository.getByName(firstName, lastName);
	}
	
	// TODO update method. Needs the name of the Worker and the data to update 
	
}
