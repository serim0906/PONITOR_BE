package pebite.Ponitor_BE.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter// 클래스내 getter 메소드 자동생성
@NoArgsConstructor //기본 생성자 자동추가
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //고객 id = auto increment
    private Long customerId;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(length = 500) //거래완료시간 = timestamp, 추후 update로 값을 채움
    private LocalDateTime endTime;

    @Column(length = 500, nullable = false)  //atm 번호, not NULL
    private String atmId;

    @Column( nullable = true)
    private boolean victim;

    @Builder
    public Customer(LocalDateTime startTime, LocalDateTime endTime, String atmId, boolean victim){
        this.startTime = startTime;
        this.endTime = endTime;
        this.atmId = atmId;
        this.victim = victim;
    }

    public void update(LocalDateTime endTime){
        this.endTime = endTime;
    }

}
