package com.zerobase.fastlms.banner.dto;

import com.zerobase.fastlms.banner.entity.Banner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BannerDto {
    
    Long id;

    String altTitle;
    String imgPath;
    String imgSaveName;
    String imgRealName;
    String url;
    String target;
    int sortValue;
    boolean usingYn;

    LocalDateTime regDt;
    
    //ADD COLUMNS
    long totalCount;
    long seq;
    
    public static BannerDto of(Banner banner) {
        return BannerDto.builder()
                .id(banner.getId())
                .altTitle(banner.getAltTitle())
                .imgPath(banner.getImgPath())
                .imgRealName(banner.getImgRealName())
                .imgSaveName(banner.getImgSaveName())
                .url(banner.getUrl())
                .target(banner.getTarget())
                .sortValue(banner.getSortValue())
                .usingYn(banner.isUsingYn())
                .regDt(banner.getRegDt())
                .build();
    }
    
    
}
