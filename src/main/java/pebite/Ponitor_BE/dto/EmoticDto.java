package pebite.Ponitor_BE.dto;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@ToString
public class EmoticDto {
    private Long anger;
    private Long annoyance;
    private Long disapproval;
    private Long disquietment;
    private Long confusion;
    private Long sadness;
    private Long suffering;
    private Long total;

    public EmoticDto(Long anger, Long annoyance, Long disapproval, Long disquietment, Long confusion, Long sadness, Long suffering, Long total) {
        this.anger = anger;
        this.annoyance = annoyance;
        this.disapproval = disapproval;
        this.disquietment = disquietment;
        this.confusion = confusion;
        this.sadness = sadness;
        this.suffering = suffering;
        this.total = total;
    }

    public Long getAnger() {
        return anger;
    }

    public void setAnger(Long anger) {
        this.anger = anger;
    }

    public Long getAnnoyance() {
        return annoyance;
    }

    public void setAnnoyance(Long annoyance) {
        this.annoyance = annoyance;
    }

    public Long getDisapproval() {
        return disapproval;
    }

    public void setDisapproval(Long disapproval) {
        this.disapproval = disapproval;
    }

    public Long getDisquietment() {
        return disquietment;
    }

    public void setDisquietment(Long disquietment) {
        this.disquietment = disquietment;
    }

    public Long getConfusion() {
        return confusion;
    }

    public void setConfusion(Long confusion) {
        this.confusion = confusion;
    }

    public Long getSadness() {
        return sadness;
    }

    public void setSadness(Long sadness) {
        this.sadness = sadness;
    }

    public Long getSuffering() {
        return suffering;
    }

    public void setSuffering(Long suffering) {
        this.suffering = suffering;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}