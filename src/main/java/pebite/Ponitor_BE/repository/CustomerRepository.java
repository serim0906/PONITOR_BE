package pebite.Ponitor_BE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pebite.Ponitor_BE.dto.CustomerSaveRequestDto;
import pebite.Ponitor_BE.model.Customer;


/*
 * JpaRepository 상속 -> 자동으로 빈 등록 (@Repository 안 달아도 됨)
 * */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
