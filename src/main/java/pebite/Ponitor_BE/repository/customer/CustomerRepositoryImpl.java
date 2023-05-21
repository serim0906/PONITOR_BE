package pebite.Ponitor_BE.repository.customer;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import pebite.Ponitor_BE.model.Customer;
import pebite.Ponitor_BE.model.QCustomer;

import javax.persistence.EntityManager;
import java.util.List;

public class CustomerRepositoryImpl extends QuerydslRepositorySupport implements CustomerRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Autowired
    EntityManager entityManager;

    public CustomerRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Customer.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Customer> findByAtmId(String atmId){
        final JPQLQuery<Customer> query;
        QCustomer customer = QCustomer.customer;

        query = from(customer)
                .where(customer.atmId.eq(atmId));

        return query.fetch();
    }

}