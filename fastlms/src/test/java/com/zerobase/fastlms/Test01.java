package com.zerobase.fastlms;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Test01 {

    @Test
    void TEST_01() {
    
        String value = "2021-06-08";
    
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(value, formatter);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    
        System.out.println();
        
    
    
    }

}
