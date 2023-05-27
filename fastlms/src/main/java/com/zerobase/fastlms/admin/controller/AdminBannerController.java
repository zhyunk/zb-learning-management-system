package com.zerobase.fastlms.admin.controller;

import com.zerobase.fastlms.admin.model.CategoryInput;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.service.BannerService;
import com.zerobase.fastlms.course.controller.BaseController;
import com.zerobase.fastlms.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Controller
public class AdminBannerController extends BaseController {
    private final BannerService bannerService;

    @GetMapping("/admin/banner/list.do")
    public String list(Model model, MemberParam parameter) {

        List<BannerDto> list = bannerService.list();
        model.addAttribute("list", list);

        return "admin/banner/list";
    }

    @GetMapping("/admin/banner/add.do")
    public String addView(Model model, CategoryInput parameter) {

//        CategoryDto dto = categoryService.select();
        model.addAttribute("dto", "디티오");

        return "admin/banner/add";
    }


    @PostMapping("/admin/banner/add.do")
    public String add(MultipartFile file, BannerInput parameter) {

        FileUtil files = new FileUtil();
        String imgPath = files.save(file).getUrlFileName();
        parameter.setImgPath(imgPath);

        bannerService.add(parameter);

        return "redirect:/admin/banner/list.do";
    }

    @PostMapping("/admin/banner/delete.do")
    public String del(Model model, CategoryInput parameter) {

//        boolean result = categoryService.del(parameter.getId());

        return "redirect:/admin/banner/list.do";
    }

    @PostMapping("/admin/banner/update.do")
    public String update(Model model, CategoryInput parameter) {

//        boolean result = categoryService.update(parameter);

        return "redirect:/admin/banner/list.do";
    }

}
