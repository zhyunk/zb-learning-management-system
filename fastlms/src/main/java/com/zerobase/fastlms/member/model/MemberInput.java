package com.zerobase.fastlms.member.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class MemberInput {
    private String userId;
    private String userName;
    private String phone;
    private String password;
    
    private String newPassword;
    
    private String zipcode;
    private String addr;
    private String addrDetail;
    
}
