package pebite.Ponitor_BE.repository.atm;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import pebite.Ponitor_BE.model.Atm;
import pebite.Ponitor_BE.model.QAtm;

import javax.persistence.EntityManager;
import java.util.List;

public class AtmRepositoryImpl extends QuerydslRepositorySupport implements AtmRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Autowired
    EntityManager entityManager;

    public AtmRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Atm.class);
        this.queryFactory = queryFactory;
    }


    /*
    @Override
    public List<Atm> findByAtmBranch(String atmBranch){
        final JPQLQuery<Atm> query;
        QAtm atm = QAtm.atm;

        query = from(atm)
                .where(atm.atmId.eq(atmBranch));

        return query.fetch();
    }
     */
}