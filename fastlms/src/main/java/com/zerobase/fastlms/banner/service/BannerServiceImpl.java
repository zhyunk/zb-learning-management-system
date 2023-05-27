package com.zerobase.fastlms.banner.service;

import com.zerobase.fastlms.admin.dto.CategoryDto;
import com.zerobase.fastlms.admin.entity.Category;
import com.zerobase.fastlms.admin.model.CategoryInput;
import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.banner.mapper.BannerMapper;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.repository.BannerRepository;
import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.model.CourseParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
                .target(parameter.getTarget())
                .regDt(LocalDateTime.now())
                .build()
        );
        
        return true;
    }
    
    @Override
    public boolean update(BannerInput parameter) {
        
        Optional<Banner> optionalCategory = bannerRepository.findById(parameter.getId());
        if (optionalCategory.isPresent()) {
            Banner banner = optionalCategory.get();
            banner.setSortValue(parameter.getSortValue());
            banner.setUsingYn(parameter.isUsingYn());
            bannerRepository.save(banner);
        }
        
        return true;
    }
    
    @Override
    public boolean del(long id) {
        
        bannerRepository.deleteById(id);
        
        return true;
    }
    
    @Override
    public List<BannerDto> frontList(BannerDto parameter) {

        return bannerMapper.select(parameter);
    }
}
