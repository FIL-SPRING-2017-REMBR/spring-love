package imta.springlove.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Class used to manage the authentication. A user name is given to the method,
 * which will try to find the user in the database. If this user doesn't exist
 * then an exception is thrown.
 */
public class SpringLoveUsers implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		String sql = "SELECT * FROM User WHERE Name = ?";

		Connection conn = null;

		try {
			conn = DatabaseConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			UserDetails user = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
//				user = new UserDetails(); //TODO: instanciate the true object
			} else throw new UsernameNotFoundException(username);
			rs.close();
			ps.close();
//			return user;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		return null;
	}

}
