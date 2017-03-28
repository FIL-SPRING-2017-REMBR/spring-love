package imta.springlove.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imta.springlove.entities.Worker;
import imta.springlove.repositories.WorkerRepository;

/**
 * Controller for the worker part of the API.
 */
@RestController
@RequestMapping("/worker")
public class WorkerController {

	/**
	 * Persists a Worker entity with the given data
	 * @return the right HTTP status code
	 * @throws URISyntaxException
	 */
	@PostMapping
	public ResponseEntity<?> persistWorker(@RequestBody Worker worker) throws URISyntaxException {
		// insert it in the database
		try {
			System.out.println("############\n " + worker + " \n##########\n");
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
	@GetMapping(value = "/all")
	public List<Worker> getWorkers() throws SQLException {
		return WorkerRepository.getWorkers();
	}
	
	/**
	 * Get a Worker by it's name.
	 */
	@GetMapping(value = "/{firstName}/{lastName}")
	public Worker getByName(@PathVariable String firstName, @PathVariable String lastName) throws SQLException {
		return WorkerRepository.getByName(firstName, lastName);
	}
	
	@GetMapping(value = "/{id}")
	public Worker getById(@PathVariable int id) throws SQLException {
		return WorkerRepository.getById(id);
	} 

	@PutMapping
	public ResponseEntity<?> updateWorker(@RequestBody Worker worker) {
		try {
			WorkerRepository.persist(worker);
			return ResponseEntity.ok().build();
		} catch (SQLException e) {
			return ResponseEntity.status(500).build();
		}
	}
	
}
