package com.zerobase.fastlms.member.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class LoginHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userId;
    private LocalDateTime loginDt;
    private String ip;
    private String userAgent;
}
