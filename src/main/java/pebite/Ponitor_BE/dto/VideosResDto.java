package pebite.Ponitor_BE.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VideosResDto {
    private Long customerId;
    private String startTime;
    private String endTime;
    private String atmId;
    private Boolean victim;
    private String filePath;
    private Long withdraw;
    private Boolean phone;

    private  Long anger;
    private  Long annoyance;
    private  Long disapproval;
    private  Long disquietment;
    private  Long doubtConfusion;
    private  Long sadness;
    private  Long suffering;
    private  Long total;

}