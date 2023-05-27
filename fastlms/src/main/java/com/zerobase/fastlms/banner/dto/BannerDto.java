package com.zerobase.fastlms.banner.dto;

import com.zerobase.fastlms.banner.entity.Banner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BannerDto {
    
    Long id;

    String altTitle;
    String imgPath;
    String url;
    String target;
    int sortValue;
    boolean usingYn;

    LocalDateTime regDt;
    
    //ADD COLUMNS
    long totalCount;
    long seq;
    
    
    public static List<BannerDto> of (List<Banner> banners) {
        if (banners != null) {
            List<BannerDto> bannerList = new ArrayList<>();
            for(Banner x : banners) {
                bannerList.add(of(x));
            }
            return bannerList;
        }
        
        return null;
    }
    
    public static BannerDto of(Banner banner) {
        return BannerDto.builder()
                .id(banner.getId())
                .altTitle(banner.getAltTitle())
                .imgPath(banner.getImgPath())
                .url(banner.getUrl())
                .target(banner.getTarget())
                .sortValue(banner.getSortValue())
                .usingYn(banner.isUsingYn())
                .regDt(banner.getRegDt())
                .build();
    }
    
    
}
