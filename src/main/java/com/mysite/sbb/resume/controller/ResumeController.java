package com.mysite.sbb.resume.controller;

import com.mysite.sbb.resume.dto.ResumeDto;
import com.mysite.sbb.resume.entity.Resume;
import com.mysite.sbb.resume.service.ResumeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/resume")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    // 이력서 목록을 보여줍니다.
    @GetMapping("/list")
    public String list(Model model) {
        List<Resume> resumeList = this.resumeService.getList();
        model.addAttribute("resumeList", resumeList);
        return "resume/resumeList";
    }

    // 이력서 상세 페이지를 보여줍니다.
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        Resume resume = this.resumeService.getResume(id);
        model.addAttribute("resume", resume);
        return "resume/resumeDetail";
    }

    // GET 요청: 이력서 작성 폼 화면을 보여줍니다.
    @GetMapping("/create")
    public String createResume(Model model) {
        model.addAttribute("resumeDto", new ResumeDto());
        return "resume/resumeForm";
    }

    // POST 요청: 이력서 작성 폼 제출을 처리합니다.
    @PostMapping("/create")
    public String createResume(@Valid ResumeDto resumeDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            log.warn("Resume creation failed due to validation errors.");
            return "resume/resumeForm";
        }

        try {
            this.resumeService.create(resumeDto);
            log.info("Resume created successfully for: {}", resumeDto.getName());
            return "redirect:/resume/list";
        } catch (Exception e) {
            log.error("Error creating resume: {}", e.getMessage());
            model.addAttribute("errorMessage", "이력서 저장 중 오류가 발생했습니다.");
            return "resume/resumeForm";
        }
    }

    // [수정 기능] GET 요청: 이력서 수정 폼 화면을 보여줍니다.
    @GetMapping("/modify/{id}")
    public String modifyResume(Model model, @PathVariable("id") Long id) {
        Resume resume = this.resumeService.getResume(id);

        // Entity 내용을 DTO에 담아 폼에 전달
        ResumeDto resumeDto = new ResumeDto();
        resumeDto.setName(resume.getName());
        resumeDto.setPhone(resume.getPhone());
        resumeDto.setEmail(resume.getEmail());
        resumeDto.setTechStack(resume.getTechStack());
        resumeDto.setCareerDetails(resume.getCareerDetails());
        resumeDto.setSelfIntroduction(resume.getSelfIntroduction());

        model.addAttribute("id", id);
        model.addAttribute("resumeDto", resumeDto);
        return "resume/resumeForm";
    }

    // [수정 기능] POST 요청: 이력서 수정 폼 제출을 처리합니다.
    @PostMapping("/modify/{id}")
    public String modifyResume(@Valid ResumeDto resumeDto, BindingResult bindingResult,
                               @PathVariable("id") Long id, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("id", id);
            log.warn("Resume modification failed due to validation errors for ID: {}", id);
            return "resume/resumeForm";
        }

        try {
            Resume resume = this.resumeService.getResume(id);
            this.resumeService.modify(resume, resumeDto);
            log.info("Resume modified successfully for ID: {}", id);
            return String.format("redirect:/resume/detail/%s", id);
        } catch (RuntimeException e) {
            log.error("Error modifying resume: {}", e.getMessage());
            model.addAttribute("id", id);
            model.addAttribute("errorMessage", "이력서 수정 중 오류가 발생했습니다.");
            return "resume/resumeForm";
        }
    }

    // [삭제 기능] GET 요청: 이력서 삭제를 처리합니다.
    @GetMapping("/delete/{id}")
    public String deleteResume(@PathVariable("id") Long id) {
        try {
            Resume resume = this.resumeService.getResume(id);
            this.resumeService.delete(resume);
            log.info("Resume deleted successfully for ID: {}", id);
        } catch (RuntimeException e) {
            log.error("Error deleting resume: {}", e.getMessage());
        }
        return "redirect:/resume/list";
    }
}