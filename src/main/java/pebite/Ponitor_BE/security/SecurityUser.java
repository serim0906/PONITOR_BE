package pebite.Ponitor_BE.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import pebite.Ponitor_BE.model.Users;

public class SecurityUser extends User {
    private Users users;

    public SecurityUser(Users users) {
        super(users.getUsername(), users.getPassword(),
                AuthorityUtils.createAuthorityList(users.getAuthority()));
        this.users = users;
    }

    public Users getUsers() {
        return users;
    }

}
