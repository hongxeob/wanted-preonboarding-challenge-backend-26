package org.example.wanted.util.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    //서버
    INTERNAL_SERVER_ERROR("S001", "예기치 못한 오류가 발생했습니다."),
    UNABLE_TO_HANDLE_ERROR("S002", "처리할 수 없는 데이터입니다."),

    //유저
    NOT_FOUND_USER_BY_ID("U002", "ID로 유저를 찾을 수 없습니다.");

    private final String code;
    private final String message;
}
