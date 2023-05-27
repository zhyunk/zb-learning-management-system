package com.zerobase.fastlms.main.controller;


import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MainController {

//    private final MailComponents mailComponents; // 원래 있던 것
    private final BannerService bannerService;

    @RequestMapping("/")
    public String index(Model model) {

        List<BannerDto> list = bannerService.frontList();
        model.addAttribute("list", list);
        model.addAttribute("totalCount", list.size());

        return "index";
    }



    @RequestMapping("/error/denied")
    public String errorDenied() {
        
        return "error/denied";
    }
    
    
    
}
