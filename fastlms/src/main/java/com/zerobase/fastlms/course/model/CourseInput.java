package com.zerobase.fastlms.course.model;

import lombok.Data;

@Data
public class CourseInput {

    long id;
    long categoryId;
    String subject;
    String keyword;
    String summary;
    String contents;
    long price;
    long salePrice;
    String saleEndDtText;
    
    //삭제를 위한
    String idList;
    
    
    //ADD
    String filename;
    String urlFilename;

}
