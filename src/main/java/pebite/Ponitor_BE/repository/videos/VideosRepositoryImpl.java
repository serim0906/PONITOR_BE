package pebite.Ponitor_BE.repository.videos;


import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import pebite.Ponitor_BE.model.QVideos;
import pebite.Ponitor_BE.model.Videos;

import javax.persistence.EntityManager;
import java.util.List;

public class VideosRepositoryImpl extends QuerydslRepositorySupport implements VideosRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Autowired
    EntityManager entityManager;

    public VideosRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Videos.class);
        this.queryFactory = queryFactory;
    }


    @Override
    public List<Videos> findByCustomerId(Long customerId){
        final JPQLQuery<Videos> query;
        QVideos videos = QVideos.videos;

        query = from(videos)
                .where(videos.customerId.eq(customerId))
                .orderBy(videos.videoId.asc());

        return query.fetch();
    }

}