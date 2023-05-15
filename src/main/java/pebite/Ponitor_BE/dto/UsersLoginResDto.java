package pebite.Ponitor_BE.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsersLoginResDto {

    private String accessToken;
    private String username;
    private String authority;
    private String atmBranch;
    private String atmId;

}
