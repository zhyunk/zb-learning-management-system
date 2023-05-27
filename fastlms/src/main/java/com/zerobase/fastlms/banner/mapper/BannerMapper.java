package com.zerobase.fastlms.banner.mapper;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.model.BannerInput;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BannerMapper {
    long selectListCount(BannerInput parameter);
    List<BannerDto> select(BannerDto parameter);
}
