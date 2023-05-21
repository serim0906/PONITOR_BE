package pebite.Ponitor_BE.repository.result;


import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import pebite.Ponitor_BE.model.QResult;
import pebite.Ponitor_BE.model.Result;

import javax.persistence.EntityManager;
import java.util.Optional;

public class ResultRepositoryImpl extends QuerydslRepositorySupport implements ResultRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Autowired
    EntityManager entityManager;

    public ResultRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Result.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Optional<Result> findByCustomerId(Long customerId){

        QResult result = QResult.result;
        JPQLQuery<Result> query;

        query = from(result)
                .where(result.customerId.eq(customerId))
                .limit(1);

        return Optional.ofNullable(query.fetchOne());

    };
}