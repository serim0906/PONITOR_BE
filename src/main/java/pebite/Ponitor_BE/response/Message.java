package pebite.Ponitor_BE.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Message {

    SUCCESS("0","성공"),

    //AuthenticationException
    //401 Unauthorized
    //403 Forbidden
    ERR_1000("ERR_1000","인증에 실패하였습니다."),
    ERR_1010("ERR_1010","아이디 또는 비밀번호가 정확하지 않습니다."),
    ERR_1020("ERR_1020","토큰을 찾을 수 없거나 유효하지 않은 토큰입니다."),
    ERR_1030("ERR_1030","회원 정보를 찾을 수 없습니다."),

    //InvalidRequestException
    //400 BAD_REQUEST
    ERR_4000("ERR_4000","유효하지 않은 요청입니다."),
    ERR_4010("ERR_4010","첨부파일 업로드 처리 중 오류가 발생했습니다."),
    ERR_4020("ERR_4020","파일 암호화 처리 중 오류가 발생했습니다."),
    ERR_4030("ERR_4030","원격 서버에서 파일 쓰기/읽기 오류가 발생하였습니다.");

    private String code;

    private String message;

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }


}
