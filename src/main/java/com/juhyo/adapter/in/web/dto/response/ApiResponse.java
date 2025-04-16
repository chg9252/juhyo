package com.juhyo.adapter.in.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private String result;    // SUCCESS or ERROR
    private String message;   // 성공 또는 오류 메시지
    private T data;           // 반환 데이터

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>("SUCCESS", message, data);
    }

    public static <T> ApiResponse<T> success(T data) {
        return success("요청이 성공적으로 처리되었습니다.", data);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>("ERROR", message, null);
    }

}
