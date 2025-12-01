package com.mysite.sbb.resume.controller;

import com.mysite.sbb.resume.dto.ResumeDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/resume")
@RequiredArgsConstructor
public class ResumeController {

    // GET 요청: 이력서 작성 폼 화면을 보여줍니다.
    @GetMapping("/create")
    public String createResume(Model model) {
        model.addAttribute("resumeDto", new ResumeDto());
        return "resume/resumeForm"; // "resume/resumeForm.html" 템플릿을 반환합니다.
    }

    // POST 요청: 이력서 작성 폼 제출을 처리합니다.
    @PostMapping("/create")
    public String createResume(@Valid ResumeDto resumeDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            // 유효성 검사 실패 시 폼 화면으로 돌아가 에러 메시지를 표시합니다.
            model.addAttribute("resumeDto", resumeDto);
            log.warn("Resume creation failed due to validation errors.");
            return "resume/resumeForm";
        }

        // 실제로는 이 곳에서 Service 레이어를 호출하여 DB에 저장해야 합니다.
        // 현재는 작성된 이력서 내용을 보여주는 상세 페이지로 임시 이동합니다.

        log.info("Resume created successfully for: {}", resumeDto.getName());
        model.addAttribute("resume", resumeDto);
        return "resume/resumeDetail"; // "resume/resumeDetail.html" 템플릿을 반환합니다.
    }
}