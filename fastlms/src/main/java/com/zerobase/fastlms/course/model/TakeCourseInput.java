package com.zerobase.fastlms.course.model;

import com.zerobase.fastlms.admin.model.CommonParam;
import lombok.Data;

@Data
public class TakeCourseInput {

    long courseId;
    String userId;

    
    long takeCourseId;//take_course.id
}
