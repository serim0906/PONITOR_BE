package pebite.Ponitor_BE.controller;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;
import pebite.Ponitor_BE.dto.CustomerSaveRequestDto;
import pebite.Ponitor_BE.dto.CustomerUpdateRequestDto;
import pebite.Ponitor_BE.model.Customer;
import pebite.Ponitor_BE.repository.CustomerRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomerRepository customerRepository;

    @After
    public void tearDown() throws Exception {
        customerRepository.deleteAll();
    }


    @Test
    public void customer_register() throws Exception {

        //given
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now();
        String atmId = "ewha001";

        CustomerSaveRequestDto requestDto = CustomerSaveRequestDto.builder()
                .startTime(startTime)
                .endTime(endTime)
                .atmId(atmId)
                .build();


        String url = "http://localhost:" + port + "/users/lists";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Customer> all = customerRepository.findAll();
        assertThat(all.get(0).getEndTime()).isEqualTo(endTime);
        assertThat(all.get(0).getAtmId()).isEqualTo(atmId);
    }


    @Test
    public void Customer_update() throws Exception{
        //given
        Customer savedCustomer = customerRepository.save(Customer.builder()
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now())
                .atmId("ewha002")
                .build());

        //Long updateCustomerId = savedCustomer.getCustomerId();
        LocalDateTime expectedEndTime = LocalDateTime.now();

        CustomerUpdateRequestDto requestDto = CustomerUpdateRequestDto.builder()
                .endTime(expectedEndTime)
                .build();

        String url = "http://localhost:" + port + "/users/lists";

        HttpEntity<CustomerUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Customer> all = customerRepository.findAll();
        assertThat(all.get(0).getEndTime()).isEqualTo(expectedEndTime);


    }


}

