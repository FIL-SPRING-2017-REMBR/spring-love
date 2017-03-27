package imta.springlove.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Class used to manage the authentication. A user name is given to the method,
 * which will try to find the user in the database. If this user doesn't exist
 * then an exception is thrown.
 */
@Service
public class SpringLoveUsers implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		String sql = "SELECT * FROM User WHERE Name = ?";
		UserDetails user = null;
		Connection conn = null;

		try {
			conn = DatabaseConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				user = new User(rs.getString("username"), rs.getString("password"), this.getAuthorities());
			else
				throw new UsernameNotFoundException(username);
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
		return user;
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new UserAuthority());
	}

}
