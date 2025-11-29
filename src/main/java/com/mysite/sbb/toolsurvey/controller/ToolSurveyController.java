package com.mysite.sbb.toolsurvey.controller;

import com.mysite.sbb.toolsurvey.ExperienceEnum;
import com.mysite.sbb.toolsurvey.PreferredToolEnum;
import com.mysite.sbb.toolsurvey.dto.ToolSurveyDto;
import com.mysite.sbb.toolsurvey.entity.ToolSurvey;
import com.mysite.sbb.toolsurvey.service.ToolSurveyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/toolSurvey")
public class ToolSurveyController {

    private final ToolSurveyService toolSurveyService;

    @ModelAttribute("ExprienceEnum")
    public ExperienceEnum[] experience() {
            return ExperienceEnum.values();
    }
    @ModelAttribute("PreferredToolEnum")
    public PreferredToolEnum[] preferredTool() {
        return PreferredToolEnum.values();
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<ToolSurveyDto> toolSurveyDtoList = toolSurveyService.getToolSurveyList();
        model.addAttribute("ToolSurveyDtoList", toolSurveyDtoList);
        return "toolSurvey/toolSurveyList";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("toolSurveyDto", new ToolSurveyDto());
        return "toolSurvey/toolSurveyInputForm";
    }

    @PostMapping("/create")
    public String create(@Valid ToolSurveyDto toolSurveyDto, BindingResult bindingResult, Model model) {

        System.out.println(toolSurveyDto);

        if(bindingResult.hasErrors()){
            model.addAttribute("toolSurveyDto", toolSurveyDto);
            return "toolSurvey/toolSurveyInputForm";
        }

        toolSurveyService.create(toolSurveyDto);
        return "redirect:/toolSurvey/list";
    };

}
