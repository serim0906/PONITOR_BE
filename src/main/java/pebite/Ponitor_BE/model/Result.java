package pebite.Ponitor_BE.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@Entity
@Table(name="result")
public class Result {

    @Id
    private Long customerId;

    @Column
    private Long withdraw;

    @Column(nullable = true)
    private boolean phone;

    @Column(nullable = true)
    private Long anger;

    @Column(nullable = true)
    private Long annoyance;

    @Column(nullable = true)
    private Long disapproval;

    @Column(nullable = true)
    private Long disquietment;

    @Column(nullable = true)
    private Long confusion;

    @Column(nullable = true)
    private Long sadness;

    @Column(nullable = true)
    private Long suffering;

    @Column(nullable = true)
    private Long total;

    @Builder
    public Result(Long customerId, Long withdraw, boolean phone, Long anger, Long annoyance,
                  Long disapproval, Long disquietment, Long confusion,  Long sadness,
                  Long suffering, Long total){
        this.customerId = customerId;
        this.withdraw = withdraw;
        this.phone = phone;
        this.anger = anger;
        this.annoyance = annoyance;
        this.disapproval = disapproval;
        this.disquietment = disquietment;
        this.confusion = confusion;
        this.sadness = sadness;
        this.suffering = suffering;
        this.total = total;
    }

}
