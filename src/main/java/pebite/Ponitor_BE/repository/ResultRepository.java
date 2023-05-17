package pebite.Ponitor_BE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pebite.Ponitor_BE.model.Result;

public interface ResultRepository extends JpaRepository <Result, Long> {
}
