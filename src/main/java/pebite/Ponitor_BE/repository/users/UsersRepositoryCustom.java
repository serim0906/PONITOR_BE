package pebite.Ponitor_BE.repository.users;

import pebite.Ponitor_BE.model.Users;

import java.util.Optional;

public interface UsersRepositoryCustom {

    Optional<Users> findByUsername(String username);
    String findByPasswordEncode(String password);
}
