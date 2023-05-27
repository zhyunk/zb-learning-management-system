package com.zerobase.fastlms.banner.model;


import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Data
public class BannerInput {
    
    long id;

    String altTitle;
    String imgPath;
    String url;
    String target;
    int sortValue;
    boolean usingYn;

    LocalDateTime regDt;

    //ADD
    String filename;
    String urlFilename;

    //삭제를 위한
    String idList;
}
