package com.zerobase.fastlms.admin.mapper;


import com.zerobase.fastlms.admin.dto.LoginHistoryDto;
import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.model.MemberParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginHistoryMapper {

    long selectListCount(String userId);
    List<LoginHistoryDto> selectList(String userId);

}
