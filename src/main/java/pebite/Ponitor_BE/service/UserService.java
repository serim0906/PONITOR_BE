package pebite.Ponitor_BE.service;


import pebite.Ponitor_BE.model.Authority;
import pebite.Ponitor_BE.security.JwtTokenProvider;
import pebite.Ponitor_BE.dto.UsersLoginResDto;
import pebite.Ponitor_BE.exception.AuthenticationException;
import pebite.Ponitor_BE.model.Users;
import pebite.Ponitor_BE.repository.UsersRepository;
import pebite.Ponitor_BE.response.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class UserService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;


    public UsersLoginResDto login(String username, String password) throws AuthenticationException {

        Users users = usersRepository.findByUsername(username)
                .orElseThrow(()->new AuthenticationException(Message.ERR_1010));
        String encodedPassword = usersRepository.findByPasswordEncode(password);

        if(!encodedPassword.equals(users.getPassword())){
            throw new AuthenticationException(Message.ERR_1010);
        }

        String atmId = users.getAtmId();
        String atmBranch = users.getAtmBranch();
        String authority = users.getAuthority();
        Authority jwtRoleType = "ROLE_ADMIN".equals(authority) ? Authority.ROLE_ADMIN : Authority.ROLE_USER;

        //AccessToken 지급
        String accessToken = jwtTokenProvider.createToken(users.getUsername(), jwtRoleType, null);

        return UsersLoginResDto.builder()
                .accessToken(accessToken)
                .username(username)
                .authority(authority)
                .atmBranch(atmBranch)
                .atmId(atmId)
                .build();

    }
}
