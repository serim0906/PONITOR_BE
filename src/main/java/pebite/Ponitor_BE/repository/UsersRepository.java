package pebite.Ponitor_BE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import pebite.Ponitor_BE.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>, UsersRepositoryCustom, QuerydslPredicateExecutor<Users> {
}


