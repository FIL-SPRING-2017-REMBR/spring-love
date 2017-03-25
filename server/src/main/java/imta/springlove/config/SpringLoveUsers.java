package imta.springlove.config;

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
		// TODO load users from the database. If not found: throw the exception
		return null;
	}

}
