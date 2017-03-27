package imta.springlove.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import imta.springlove.config.DatabaseConnection;
import imta.springlove.entities.Skill;

public class SkillRepository {
	
	public static Skill getSkillById(int id) throws SQLException {
		Skill skill = null;
		Connection conn = DatabaseConnection.getConnection();
		String sql = "SELECT * FROM Skill WHERE id = "+id+";";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		if(rs.next()) {
			skill = new Skill(rs.getString("libelle"));
			skill.setId(rs.getInt("id"));
		}
		return skill;
	}
	
	public static Skill getSkillByName(String name) throws SQLException {
		Skill skill = null;
		Connection conn = DatabaseConnection.getConnection();
		String sql = "SELECT * FROM Skill WHERE libelle = '"+name+"';";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		if(rs.next()) {
			skill = new Skill(rs.getString("libelle"));
			skill.setId(rs.getInt("id"));
		}
		return skill;
	}
	
	public static List<Skill> getSkills() throws SQLException {
		List<Skill> skills = new ArrayList<Skill>();
		
		//Get Workers
		Connection conn = DatabaseConnection.getConnection();
		String sql = "SELECT * FROM Skill;";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		while(rs.next()) {
			Skill skill = new Skill(rs.getString("libelle"));
			skill.setId(rs.getInt("id"));
			skills.add(skill);
		}
		return skills;
	}
	
	public static int persist(Skill skill) throws SQLException {
		if(skill.getId() == 0) {
			return insert(skill);
		} else {
			return modify(skill);
		}
	}
	
	private static int insert(Skill skill) throws SQLException {
		int skillId = 0;
		Connection conn = DatabaseConnection.getConnection();
		String sqlSkill = "INSERT INTO Skill " +
			"(libelle, url) VALUES (?, ?);";
		PreparedStatement psSkill = conn.prepareStatement(sqlSkill,Statement.RETURN_GENERATED_KEYS);
		psSkill.setString(1, skill.getName());
		psSkill.setString(2, skill.getUrl());
		psSkill.executeUpdate();
		ResultSet generatedKeysSkill = psSkill.getGeneratedKeys();
		if (generatedKeysSkill.next()) {
			skillId = generatedKeysSkill.getInt(1);
		}
		psSkill.close();
		conn.close();
		return skillId;
	}
	
	private static int modify(Skill skill) throws SQLException {
		int skillId = skill.getId();
		Connection conn = DatabaseConnection.getConnection();
		String sqlSkill = "ALTER TABLE Skill " +
			"SET libelle = ? WHERE id = ?;";
		PreparedStatement psSkill = conn.prepareStatement(sqlSkill);
		psSkill.setString(1, skill.getName());
		psSkill.setInt(2, skillId);
		psSkill.executeUpdate();
		psSkill.close();
		conn.close();
		return skillId;
	}

}
