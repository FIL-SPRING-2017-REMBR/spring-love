package imta.springlove.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import imta.springlove.config.DatabaseConnection;
import imta.springlove.entities.Appetence;
import imta.springlove.entities.Experience;
import imta.springlove.entities.Maturity;
import imta.springlove.entities.Skill;
import imta.springlove.entities.Trend;
import imta.springlove.entities.Worker;

public class WorkerRepository {
	
	public static void persist(Worker worker) throws SQLException{
		if(worker.getId() == 0) {
			insert(worker);
		} else {
			modify(worker);
		}
	}
	
	public static Worker getById(int id) throws SQLException {
		Worker worker = null;
		//Get Worker
		Connection conn = DatabaseConnection.getConnection();
		String sql = "SELECT * FROM Worker WHERE id = "+id+";";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		if(rs.next()) {
			worker = new Worker(rs.getString("firstName"), rs.getString("lastName"), null, null, null);
			worker.setId(rs.getInt("id"));
			
			//Get resources
			loadRessoucesFor(worker);
			
			//Get skills
			loadSkillsFor(worker);
			
			//Get experiences
			worker.setExperiences(ExperienceRepository.findByWorkerId(id));
		} else {
			//TODO: lever une exception
			return null;
		}
		return worker;
	}
	
	public static List<Worker> getWorkers() throws SQLException {
		List<Worker> workers = new ArrayList<Worker>();
		
		//Get Workers
		Connection conn = DatabaseConnection.getConnection();
		String sql = "SELECT * FROM Worker;";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		while(rs.next()) {
			Worker worker = new Worker(rs.getString("firstName"), rs.getString("lastName"), null, null, null);
			worker.setId(rs.getInt("id"));
			
			//Get resources
			loadRessoucesFor(worker);
			
			//Get skills
			loadSkillsFor(worker);
			
			//Get experiences
			worker.setExperiences(ExperienceRepository.findByWorkerId(worker.getId()));
			workers.add(worker);
		}
		return workers;
	}
	
	public static Worker getByName(String firstName, String lastName) throws SQLException{
		Worker worker = null;
		//Get Worker
		Connection conn = DatabaseConnection.getConnection();
		String sql = "SELECT * FROM Worker WHERE firstName = "+firstName+" AND lastName = "+lastName+";";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		if(rs.next()) {
			worker = new Worker(rs.getString("firstName"), rs.getString("lastName"), null, null, null);
			worker.setId(rs.getInt("id"));
			
			//Get resources
			loadRessoucesFor(worker);
			
			//Get skills
			loadSkillsFor(worker);
			
			//Get experiences
			worker.setExperiences(ExperienceRepository.findByWorkerId(worker.getId()));
			
		} else {
			//TODO: lever une exception
			return null;
		}
		return worker;
	}
	
	private static void loadRessoucesFor(Worker worker) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();
		String sql = "SELECT * FROM Resource where FK_workerId = "+worker.getId()+";";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		Map<String,String> resources = new HashMap<String,String>();
		while(rs.next()) {
			resources.put(rs.getString("libelle"), rs.getString("url"));
		}
		worker.setResources(resources);
	}
	
	private static void loadSkillsFor(Worker worker) throws SQLException{
		Connection conn = DatabaseConnection.getConnection();
		String sql = "SELECT s.libelle AS libelleSkill, m.id AS idMaturity, t.id as idTrend "
				+ "FROM WorkerSkill AS ws"
				+ "INNER JOIN Skill AS s ON (s.id = ws.FK_skillId)"
				+ "INNER JOIN Maturity AS m ON (m.id = ws.FK_maturityId)"
				+ "INNER JOIN Trend AS t ON (t.id = ws.FK_trendId)"
				+ "WHERE ws.FK_workerId = "+worker.getId()+";";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		Map<Skill, Appetence> skills = new HashMap<Skill,Appetence>();
		while(rs.next()) {
			skills.put(new Skill(rs.getString("libelleSkill")),
					new Appetence(Maturity.getMaturity(rs.getInt("idMaturity")), Trend.getTrend(rs.getInt("idTrend"))));
		}
		worker.setSkills(skills);
	}
	
	private static void modify(Worker worker) throws SQLException {
		//Persist Worker
		Connection conn = DatabaseConnection.getConnection();
		String sqlWorker = "UPDATE Worker " +
				"SET firstName = ?, lastName = ? WHERE id = ?";
		PreparedStatement psWorker = conn.prepareStatement(sqlWorker);
		psWorker.setString(1, worker.getFirstName());
		psWorker.setString(2, worker.getLastName());
		psWorker.setInt(3, worker.getId());
		psWorker.executeUpdate();
		psWorker.close();
		
		//Modify Worker's Resources
		String sqlDropResources = "DELETE * FROM Resource WHERE FK_workerId = ?;";
		PreparedStatement psDropResources = conn.prepareStatement(sqlDropResources);
		psDropResources.setInt(1, worker.getId());
		psDropResources.executeUpdate();
		psDropResources.close();
		for (Map.Entry<String,String> entry : worker.getResources().entrySet()) {
			String sqlResources = "INSERT INTO Resource " +
				"(FK_workerId, libelle, url) VALUES (?, ?, ?)";
			PreparedStatement psResources = conn.prepareStatement(sqlResources);
			psResources.setInt(1, worker.getId());
			psResources.setString(2, entry.getKey());
			psResources.setString(3, entry.getValue());
			psResources.executeUpdate();
			psResources.close();
		}
		
		//Modify Worker's Skills
		String sqlDropSkill = "DELETE * FROM WorkerSkill WHERE FK_workerId = ?;";
		PreparedStatement psDropSkill = conn.prepareStatement(sqlDropSkill);
		psDropSkill.setInt(1, worker.getId());
		psDropSkill.executeUpdate();
		psDropSkill.close();
		for (Map.Entry<Skill,Appetence> entry : worker.getSkills().entrySet()) {
			int skillId = 0;
			String sqlSkill = "INSERT INTO Skill " +
				"(libelle) VALUES (?);";
			PreparedStatement psSkill = conn.prepareStatement(sqlSkill,Statement.RETURN_GENERATED_KEYS);
			psSkill.setString(1, entry.getKey().getName());
			psSkill.executeUpdate();
			ResultSet generatedKeysSkill = psSkill.getGeneratedKeys();
			if (generatedKeysSkill.next()) {
				skillId = generatedKeysSkill.getInt(1);
	        }
			psSkill.close();
			
			String sqlAppetence = "INSERT INTO WorkerSkill " +
				"(FK_workerId, FK_skillId, FK_maturityId, FK_trendId) VALUES (?, ?, ?, ?);";
			PreparedStatement psAppetence = conn.prepareStatement(sqlAppetence,Statement.RETURN_GENERATED_KEYS);
			psSkill.setInt(1, worker.getId());
			psSkill.setInt(2, skillId);
			psSkill.setInt(3, entry.getValue().getMaturity().getValue());
			psSkill.setInt(4, entry.getValue().getTrend().getValue());
		}
		
		//Modify Worker's Experiences
		for (Experience experience : worker.getExperiences()) {
			ExperienceRepository.persist(experience, worker.getId());
		}
		
		conn.close();
	}

	private static void insert(Worker worker) throws SQLException{
		//Persist Worker
		Connection conn = DatabaseConnection.getConnection();
		String sqlWorker = "INSERT INTO Worker " +
				"(firstName, lastName) VALUES (?, ?)";
		PreparedStatement psWorker = conn.prepareStatement(sqlWorker,Statement.RETURN_GENERATED_KEYS);
		psWorker.setString(1, worker.getFirstName());
		psWorker.setString(2, worker.getLastName());
		psWorker.executeUpdate();
		ResultSet generatedKeys = psWorker.getGeneratedKeys();
		if (generatedKeys.next()) {
              worker.setId(generatedKeys.getInt(1));
           }
		psWorker.close();
		
		//Persist Worker's Resources
		for (Map.Entry<String,String> entry : worker.getResources().entrySet()) {
			String sqlResources = "INSERT INTO Resource " +
				"(FK_workerId, libelle, url) VALUES (?, ?, ?)";
			PreparedStatement psResources = conn.prepareStatement(sqlResources);
			psResources.setInt(1, worker.getId());
			psResources.setString(2, entry.getKey());
			psResources.setString(3, entry.getValue());
			psResources.executeUpdate();
			psResources.close();
		}
		
		//Persist Worker's skills
		for (Map.Entry<Skill,Appetence> entry : worker.getSkills().entrySet()) {
			int skillId = 0;
			String sqlSkill = "INSERT INTO Skill " +
				"(libelle) VALUES (?);";
			PreparedStatement psSkill = conn.prepareStatement(sqlSkill,Statement.RETURN_GENERATED_KEYS);
			psSkill.setString(1, entry.getKey().getName());
			psSkill.executeUpdate();
			ResultSet generatedKeysSkill = psSkill.getGeneratedKeys();
			if (generatedKeysSkill.next()) {
				skillId = generatedKeysSkill.getInt(1);
	           }
			psSkill.close();
			
			String sqlAppetence = "INSERT INTO WorkerSkill " +
				"(FK_workerId, FK_skillId, FK_maturityId, FK_trendId) VALUES (?, ?, ?, ?);";
			PreparedStatement psAppetence = conn.prepareStatement(sqlAppetence,Statement.RETURN_GENERATED_KEYS);
			psSkill.setInt(1, worker.getId());
			psSkill.setInt(2, skillId);
			psSkill.setInt(3, entry.getValue().getMaturity().getValue());
			psSkill.setInt(4, entry.getValue().getTrend().getValue());
		}
		
		//Persist Worker's Experiences
		for (Experience experience : worker.getExperiences()) {
			ExperienceRepository.persist(experience, worker.getId());
		}
	}
	
}
