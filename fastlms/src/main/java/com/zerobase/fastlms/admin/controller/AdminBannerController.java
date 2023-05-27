package com.zerobase.fastlms.admin.controller;

import com.zerobase.fastlms.admin.model.CategoryInput;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.service.BannerService;
import com.zerobase.fastlms.course.controller.BaseController;
import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping(value = {"/admin/banner/add.do", "/admin/banner/edit.do"})
    public String addView(Model model, HttpServletRequest request, BannerInput parameter) {

        Banner banner = new Banner();
        boolean editMode = request.getRequestURI().contains("/edit.do");
        if (editMode) {
            banner = bannerService.findById(parameter.getId());
        }

        model.addAttribute("editMode", editMode);
        model.addAttribute("banner", banner);

        return "admin/banner/add";
    }


    @PostMapping(value = {"/admin/banner/add.do", "/admin/banner/edit.do"})
    public String add(HttpServletRequest request, MultipartFile file, BannerInput parameter) {

        if (file != null) {
            String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
            if (extension != null) {
                FileUtil files = new FileUtil();
                String imgPath = files.save(file).getUrlFileName();
                parameter.setImgPath(imgPath);
            }
        }

        boolean editMode = request.getRequestURI().contains("/edit.do");
        if (editMode) {
            bannerService.update(parameter);

        } else {
            bannerService.add(parameter);
        }


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
