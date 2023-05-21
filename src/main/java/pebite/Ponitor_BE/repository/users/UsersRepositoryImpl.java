package pebite.Ponitor_BE.repository.users;


import pebite.Ponitor_BE.model.QUsers;
import pebite.Ponitor_BE.model.Users;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Optional;

public class UsersRepositoryImpl extends QuerydslRepositorySupport implements UsersRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Autowired
    EntityManager entityManager;

    public UsersRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Users.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Optional<Users> findByUsername(String username){

        QUsers users = QUsers.users;
        JPQLQuery<Users> query;

        query = from(users)
                .where(users.username.eq(username));

        return Optional.ofNullable(query.fetchOne());

    }

    @Override
    public String findByPasswordEncode(String password) {

        String sql = "select md5(?0)";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(0, password);

        return (String)query.getSingleResult();
    }
}