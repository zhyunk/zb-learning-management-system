package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.member.entity.LoginHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LoginHistoryDto {
    
    String userId;
    String ip;
    String userAgent;
    LocalDateTime loginDt;

    //추가컬럼
    long totalCount;
    long seq;

    public static LoginHistoryDto of(LoginHistory history) {
        
        return LoginHistoryDto.builder()
                .userId(history.getUserId())
                .ip(history.getIp())
                .userAgent(history.getUserAgent())
                .loginDt(history.getLoginDt())
                .build();
    }


    public String getLoginDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        return loginDt != null ? loginDt.format(formatter) : "";
    }

}
