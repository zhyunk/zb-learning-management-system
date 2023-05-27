package com.zerobase.fastlms.banner.service;

import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.banner.mapper.BannerMapper;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BannerServiceImpl implements BannerService {
    
    private final BannerRepository bannerRepository;
    private final BannerMapper bannerMapper;
    
    private Sort getSortBySortValueDesc() {
        return Sort.by(Sort.Direction.DESC, "sortValue");
    }
    
    @Override
    public List<BannerDto> list() {

        long totalCount = bannerRepository.count();
        List<Banner> banners = bannerRepository.findAll(getSortBySortValueDesc());
        List<BannerDto> list = new ArrayList<>();
        int seq = 0;
        for (Banner banner : banners) {
            BannerDto dto = BannerDto.of(banner);
            dto.setTotalCount(totalCount);
            dto.setSeq(totalCount - seq++);
            list.add(dto);
        }

        return list;
    }
    
    @Override
    public boolean add(BannerInput parameter) {

        bannerRepository.save(Banner
                .builder()
                .altTitle(parameter.getAltTitle())
                .url(parameter.getUrl())
                .usingYn(parameter.isUsingYn())
                .sortValue(parameter.getSortValue())
                .imgPath(parameter.getImgPath())
                .imgRealName(parameter.getImgRealName())
                .imgSaveName(parameter.getImgSaveName())
                .target(parameter.getTarget())
                .regDt(LocalDateTime.now())
                .build()
        );
        
        return true;
    }
    
    @Override
    public boolean update(BannerInput parameter) {

        Optional<Banner> optionalBanner = bannerRepository.findById(parameter.getId());
        if (optionalBanner.isPresent()) {
            Banner banner = optionalBanner.get();
            banner.setId(parameter.getId());
            banner.setAltTitle(parameter.getAltTitle());
            banner.setTarget(parameter.getTarget());
            banner.setUrl(parameter.getUrl());
            banner.setSortValue(parameter.getSortValue());
            banner.setUsingYn(parameter.isUsingYn());

            if (parameter.getImgPath() != null) {
                banner.setImgPath(parameter.getImgPath());
                banner.setImgRealName(parameter.getImgRealName());
                banner.setImgSaveName(parameter.getImgSaveName());

            }

            bannerRepository.save(banner);
        }
        
        return true;
    }
    
    @Override
    public boolean del(String idList) {

        if (idList != null && idList.length() > 0) {
            String[] ids = idList.split(",");
            for (String x : ids) {
                long id = 0L;
                try {
                    id = Long.parseLong(x);
                } catch (Exception e) {
                }

                if (id > 0) {
                    bannerRepository.deleteById(id);
                }
            }
        }
        return true;
    }

    @Override
    public Banner findById(long id) {

        return bannerRepository.findById(id).get();
    }

    @Override
    public List<BannerDto> frontList(BannerDto parameter) {

        return bannerMapper.select(parameter);
    }
}
