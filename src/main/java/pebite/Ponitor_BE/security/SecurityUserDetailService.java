package pebite.Ponitor_BE.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pebite.Ponitor_BE.model.Users;
import pebite.Ponitor_BE.repository.users.UsersRepository;

import java.util.Optional;

@Service
public class SecurityUserDetailService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> optionalUsers = usersRepository.findByUsername(username);
        if(optionalUsers.isEmpty()) {
            throw new UsernameNotFoundException(username + " 사용자가 존재하지 않습니다.");
        } else {
            Users users = optionalUsers.get();
            return new SecurityUser(users);
        }

    }
}
