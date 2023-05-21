package pebite.Ponitor_BE.repository.result;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import pebite.Ponitor_BE.model.Result;

import java.util.Optional;

public interface ResultRepository extends JpaRepository<Result, Long>, ResultRepositoryCustom, QuerydslPredicateExecutor<ResultRepository> {

}
