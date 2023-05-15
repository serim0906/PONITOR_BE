package pebite.Ponitor_BE.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Message {

    //AuthenticationException
    //401 Unauthorized
    //403 Forbidden
    ERR_1000("ERR_1000","인증에 실패하였습니다."),
    ERR_1010("ERR_1010","아이디 또는 비밀번호가 정확하지 않습니다."),
    ERR_1020("ERR_1020","토큰을 찾을 수 없거나 유효하지 않은 토큰입니다."),
    ERR_1030("ERR_1030","회원 정보를 찾을 수 없습니다.");

    private String code;

    private String message;

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }


}
