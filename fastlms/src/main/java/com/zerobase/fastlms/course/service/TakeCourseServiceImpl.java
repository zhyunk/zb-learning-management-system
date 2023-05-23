package com.zerobase.fastlms.course.service;

import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.dto.TakeCourseDto;
import com.zerobase.fastlms.course.entity.Course;
import com.zerobase.fastlms.course.entity.TakeCourse;
import com.zerobase.fastlms.course.entity.TakeCourseCode;
import com.zerobase.fastlms.course.mapper.CourseMapper;
import com.zerobase.fastlms.course.mapper.TakeCourseMapper;
import com.zerobase.fastlms.course.model.*;
import com.zerobase.fastlms.course.repository.CourseRepository;
import com.zerobase.fastlms.course.repository.TakeCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TakeCourseServiceImpl implements TakeCourseService {

    private final TakeCourseRepository takeCourseRepository;
    private final TakeCourseMapper takeCourseMapper;
    
    
    @Override
    public List<TakeCourseDto> list(TakeCourseParam parameter) {
        
        long totalCount = takeCourseMapper.selectListCount(parameter);
        
        List<TakeCourseDto> list = takeCourseMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (TakeCourseDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }
        
        return list;
        
        
    }
    
    @Override
    public TakeCourseDto detail(long id) {
        
        Optional<TakeCourse> optionalTakeCourse = takeCourseRepository.findById(id);
        if (optionalTakeCourse.isPresent()) {
            return TakeCourseDto.of(optionalTakeCourse.get());
        }
        return null;
    }
    
    @Override
    public ServiceResult updateStatus(long id, String status) {
        
        Optional<TakeCourse> optionalTakeCourse = takeCourseRepository.findById(id);
        if (!optionalTakeCourse.isPresent()) {
            return new ServiceResult(false, "수강 정보가 존재하지 않습니다.");
        }
        
        TakeCourse takeCourse = optionalTakeCourse.get();
        
        takeCourse.setStatus( status);
        takeCourseRepository.save(takeCourse);
        
        return new ServiceResult(true);
    }
    
    @Override
    public List<TakeCourseDto> myCourse(String userId) {
        
        TakeCourseParam parameter = new TakeCourseParam();
        parameter.setUserId(userId);
        List<TakeCourseDto> list = takeCourseMapper.selectListMyCourse(parameter);
        
        return list;
    }
    
    @Override
    public ServiceResult cancel(long id) {
    
        Optional<TakeCourse> optionalTakeCourse = takeCourseRepository.findById(id);
        if (!optionalTakeCourse.isPresent()) {
            return new ServiceResult(false, "수강 정보가 존재하지 않습니다.");
        }
        
        TakeCourse takeCourse = optionalTakeCourse.get();
        
        takeCourse.setStatus(TakeCourseCode.STATUS_CANCEL);
        takeCourseRepository.save(takeCourse);
        
        return new ServiceResult();
    }
}


























