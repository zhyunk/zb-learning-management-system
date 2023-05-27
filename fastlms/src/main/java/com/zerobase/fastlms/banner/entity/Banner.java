package com.zerobase.fastlms.banner.entity;


import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@DynamicUpdate
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
