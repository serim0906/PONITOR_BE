package pebite.Ponitor_BE.repository.atm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import pebite.Ponitor_BE.model.Atm;

@Repository
public interface AtmRepository extends JpaRepository<Atm, String>, AtmRepositoryCustom, QuerydslPredicateExecutor<Atm> {
}
