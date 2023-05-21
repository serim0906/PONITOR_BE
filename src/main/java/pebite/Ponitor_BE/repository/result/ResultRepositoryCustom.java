package pebite.Ponitor_BE.repository.result;

import pebite.Ponitor_BE.model.Result;

import java.util.Optional;

public interface ResultRepositoryCustom {
    Optional<Result> findByCustomerId(Long customerId);
}