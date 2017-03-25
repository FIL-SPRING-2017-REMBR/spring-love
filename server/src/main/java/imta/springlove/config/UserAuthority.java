package imta.springlove.config;

import org.springframework.security.core.GrantedAuthority;

/**
 * Represents the role "USER"
 */
public class UserAuthority implements GrantedAuthority {

	private static final long serialVersionUID = -1896597720778255811L;

	@Override
	public String getAuthority() {
		return "USER";
	}

}
