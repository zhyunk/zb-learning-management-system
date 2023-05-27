package com.zerobase.fastlms.banner.service;

import com.zerobase.fastlms.admin.dto.CategoryDto;
import com.zerobase.fastlms.admin.model.CategoryInput;
import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.banner.model.BannerInput;

import java.util.List;

public interface BannerService {
    
    List<BannerDto> list();
    
    /**
     * 배너 추가
     */
    boolean add(BannerInput banner);
    
    /**
     * 배너 수정
     */
    boolean update(BannerInput parameter);
    
    /**
     * 배너 삭제
     */
    boolean del(long id);

    /**
     * 배너 상세 정보
     */
    Banner findById(long id);

    /**
     * 프론트 배너 정보
     */
    List<BannerDto> frontList(BannerDto parameter);
    
    

}
