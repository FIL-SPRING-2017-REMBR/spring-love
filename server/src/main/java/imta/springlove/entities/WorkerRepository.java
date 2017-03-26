package imta.springlove.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import imta.springlove.config.DatabaseConnection;

public class WorkerRepository {
	
	public static void persist(Worker worker){
		if(worker.getId() == 0) {
			insert(worker);
		} else {
			modify(worker);
		}
	}
	
	public static Worker getById() {
		return null;
	}
	
	private static void modify(Worker worker) {
		Connection conn = null;
		try {
			//Persist Worker
			conn = DatabaseConnection.getConnection();
			String sqlWorker = "UPDATE Worker " +
					"SET firstName = ?, lastName = ? WHERE id = ?";
			PreparedStatement psWorker = conn.prepareStatement(sqlWorker);
			psWorker.setString(1, worker.getFirstName());
			psWorker.setString(2, worker.getLastName());
			psWorker.setInt(3, worker.getId());
			psWorker.executeUpdate();
			psWorker.close();
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}

	private static void insert(Worker worker){
		Connection conn = null;

		try {
			//Persist Worker
			conn = DatabaseConnection.getConnection();
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
				if (generatedKeys.next()) {
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
			
		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
		//Persist Worker's Experiences
		for (Experience experience : worker.getExperiences()) {
//			ExperienceRepository.persist(experience, workerId);
		}
	}
	
}
