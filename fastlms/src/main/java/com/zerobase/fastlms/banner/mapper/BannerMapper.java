package com.zerobase.fastlms.banner.mapper;

import com.zerobase.fastlms.banner.dto.BannerDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BannerMapper {
    List<BannerDto> select(BannerDto parameter);
}
