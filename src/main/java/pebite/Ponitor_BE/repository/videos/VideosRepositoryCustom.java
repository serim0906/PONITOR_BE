package pebite.Ponitor_BE.repository.videos;

import pebite.Ponitor_BE.model.Videos;

import java.util.List;

public interface VideosRepositoryCustom {
    List<Videos> findByCustomerId(Long customerId);
}