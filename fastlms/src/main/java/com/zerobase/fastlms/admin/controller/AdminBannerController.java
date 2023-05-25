package com.zerobase.fastlms.admin.controller;

import com.zerobase.fastlms.admin.model.CategoryInput;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.course.controller.BaseController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AdminBannerController extends BaseController {


    @GetMapping("/admin/banner/list.do")
    public String list(Model model, MemberParam parameter) {

//        List<CategoryDto> list = categoryService.list();
//        model.addAttribute("list", list);

        return "admin/banner/list";
    }

    @GetMapping("/admin/banner/add.do")
    public String addView(Model model, CategoryInput parameter) {

//        CategoryDto dto = categoryService.select();
        model.addAttribute("dto", "디티오");

        return "admin/banner/add";
    }

    @PostMapping("/admin/banner/add.do")
    public String add(Model model, CategoryInput parameter) {

//        boolean result = categoryService.add(parameter.getCategoryName());

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
