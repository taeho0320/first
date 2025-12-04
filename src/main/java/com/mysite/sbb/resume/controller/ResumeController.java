package com.mysite.sbb.resume.controller;

import com.mysite.sbb.resume.dto.ResumeDto;
import com.mysite.sbb.resume.entity.Resume; // Entity 추가
import com.mysite.sbb.resume.service.ResumeService; // Service 추가
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable; // PathVariable 추가
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/resume")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService; // Service 주입

    // [추가된 기능] 이력서 목록을 보여줍니다.
    @GetMapping("/list")
    public String list(Model model) {
        List<Resume> resumeList = this.resumeService.getList();
        model.addAttribute("resumeList", resumeList);
        return "resume/resumeList"; // 새로 만들 템플릿 파일입니다.
    }

    // [추가된 기능] 이력서 상세 페이지를 보여줍니다.
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        Resume resume = this.resumeService.getResume(id);
        model.addAttribute("resume", resume);
        return "resume/resumeDetail"; // 기존의 상세 템플릿을 재활용합니다.
    }




    // GET 요청: 이력서 작성 폼 화면을 보여줍니다.
    @GetMapping("/create")
    public String createResume(Model model) {
        model.addAttribute("resumeDto", new ResumeDto());
        return "resume/resumeForm";
    }

    // POST 요청: 이력서 작성 폼 제출을 처리하고 목록으로 리다이렉트합니다.
    @PostMapping("/create")
    public String createResume(@Valid ResumeDto resumeDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("resumeDto", resumeDto);
            log.warn("Resume creation failed due to validation errors.");
            return "resume/resumeForm";
        }

        // Service 레이어를 호출하여 DB에 저장합니다.
        try {
            this.resumeService.create(resumeDto);
            log.info("Resume created successfully for: {}", resumeDto.getName());
            return "redirect:/resume/list"; // [수정] 저장 성공 후 목록 페이지로 리다이렉트
        } catch (Exception e) {
            log.error("Error creating resume: {}", e.getMessage());
            model.addAttribute("resumeDto", resumeDto);
            model.addAttribute("errorMessage", "이력서 저장 중 오류가 발생했습니다.");
            return "resume/resumeForm";
        }
    }
}