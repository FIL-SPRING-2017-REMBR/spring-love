package imta.springlove.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import imta.springlove.config.DatabaseConnection;
import imta.springlove.entities.Experience;
import imta.springlove.entities.TypeExperience;

public class ExperienceRepository {
	
	public static List<Experience> findByWorkerId(int id) throws SQLException {
		List<Experience> experiences = new ArrayList<Experience>();
		//Get Worker
		Connection conn = DatabaseConnection.getConnection();
		String sql = "SELECT * FROM Experience AS e "
				+ "INNER JOIN WorkerExperience AS we ON (e.id = we.FK_workerId) "
				+ "WHERE we.FK_workerId = "+id+";";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		while(rs.next()) {
			Experience experience = new Experience(rs.getString("organisation"),
					rs.getString("role"),
					rs.getString("url"),
					TypeExperience.getTypeExperience(rs.getInt("FK_typeExperienceId")),
					rs.getString("url"),
					rs.getDate("beginDate"),
					rs.getDate("endDate"),
					rs.getString("description"));
			experience.setId(rs.getInt("id"));
			experiences.add(experience);
		}
		return experiences;
	}
	
	public static void persist(Experience experience, int workerId) throws SQLException {
		if(experience.getId() == 0)
			insert(experience,workerId);
		else
			modify(experience,workerId);
	}
	
	private static void insert(Experience experience, int workerId) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();

		//Experience
		String sqlExperience = "INSERT INTO Experience " +
				"(FK_typeExperienceId, organisation, role, beginDate, endDate, url, description) "
				+ "VALUES (?,?,?,?,?,?);";
		PreparedStatement psExperience = conn.prepareStatement(sqlExperience,Statement.RETURN_GENERATED_KEYS);
		psExperience.setInt(1, experience.getType().getValue());
		psExperience.setString(2, experience.getOrganisation());
		psExperience.setString(3, experience.getRole());
		psExperience.setDate(4, new java.sql.Date(experience.getTemporalRange()[0].getTime()));
		psExperience.setDate(5, new java.sql.Date(experience.getTemporalRange()[1].getTime()));
		psExperience.setString(6, experience.getUrl());
		psExperience.setString(7, experience.getDescription());
		psExperience.executeUpdate();
		ResultSet generateExperience = psExperience.getGeneratedKeys();
		int experienceId = 0;
		if (generateExperience.next()) {
			experienceId = generateExperience.getInt(1);
        }
		psExperience.close();
		
		//Link with worker
		String sqlWorkerExperience = "INSERT INTO WorkerExperience " +
				"(FK_workerId, FK_experienceId) VALUES (?,?);";
		PreparedStatement psWorkerExperience = conn.prepareStatement(sqlWorkerExperience);
		psWorkerExperience.setInt(1, workerId);
		psWorkerExperience.setInt(2, experienceId);
		psWorkerExperience.executeUpdate();
		psWorkerExperience.close();
		conn.close();
	}
	
	private static void modify(Experience experience, int workerId) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();
		
		//Experience
		String sqlExperience = "ALTER TABLE Experience " +
				"SET FK_typeExperienceId = ?, organisation = ?, role = ?, beginDate = ?, endDate = ?, "
				+ "url = ?, description = ?;";
		PreparedStatement psExperience = conn.prepareStatement(sqlExperience);
		psExperience.setInt(1, experience.getType().getValue());
		psExperience.setString(2, experience.getOrganisation());
		psExperience.setString(3, experience.getRole());
		psExperience.setDate(4, new java.sql.Date(experience.getTemporalRange()[0].getTime()));
		psExperience.setDate(5, new java.sql.Date(experience.getTemporalRange()[1].getTime()));
		psExperience.setString(6, experience.getUrl());
		psExperience.setString(7, experience.getDescription());
		psExperience.executeUpdate();
		psExperience.close();
	}

}
