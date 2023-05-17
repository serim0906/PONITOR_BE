package pebite.Ponitor_BE.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pebite.Ponitor_BE.model.Result;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
public class ResultSaveRequestDto {
    private Long customerId;
    private Long withdraw;
    private boolean phone;
    private Long anger;
    private Long annoyance;
    private Long disapproval;
    private Long disquietment;
    private Long doubtConfusion;
    private Long sadness;
    private Long suffering;
    private Long total;
    @Builder
    public ResultSaveRequestDto (Long customerId, Long withdraw, boolean phone, Long anger, Long annoyance,
                                 Long disapproval, Long disquietment, Long doubtConfusion,  Long sadness,
                                 Long suffering, Long total){
        this.customerId = customerId;
        this.withdraw = withdraw;
        this.phone = phone;
        this.anger = anger;
        this.annoyance = annoyance;
        this.disapproval = disapproval;
        this.disquietment = disquietment;
        this.doubtConfusion = doubtConfusion;
        this.sadness = sadness;
        this.suffering = suffering;
        this.total = total;

    }

    public Result toEntity(){
        return Result.builder()
                .customerId(customerId)
                .withdraw(withdraw)
                .phone(phone).anger(anger).annoyance(annoyance).disapproval(disapproval).disquietment(disquietment)
                .doubtConfusion(doubtConfusion).sadness(sadness).suffering(suffering).total(total)
                .build();
    }

}
