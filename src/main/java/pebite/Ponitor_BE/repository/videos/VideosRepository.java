package pebite.Ponitor_BE.repository.videos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import pebite.Ponitor_BE.model.Videos;

@Repository
public interface VideosRepository extends JpaRepository<Videos, Long>, VideosRepositoryCustom, QuerydslPredicateExecutor<Videos> {
}