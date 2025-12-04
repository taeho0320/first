package com.mysite.sbb.resume.service;

import com.mysite.sbb.resume.dto.ResumeDto;
import com.mysite.sbb.resume.entity.Resume;
import com.mysite.sbb.resume.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;

    // 이력서 목록 조회
    public List<Resume> getList() {
        return this.resumeRepository.findAll();
    }

    // 이력서 상세 조회
    public Resume getResume(Long id) {
        Optional<Resume> resume = this.resumeRepository.findById(id);
        if (resume.isPresent()) {
            return resume.get();
        } else {
            throw new RuntimeException("Resume not found");
        }
    }

    // 이력서 생성 및 저장
    public void create(ResumeDto resumeDto) {
        Resume resume = Resume.builder()
                .name(resumeDto.getName())
                .phone(resumeDto.getPhone())
                .email(resumeDto.getEmail())
                .techStack(resumeDto.getTechStack())
                .careerDetails(resumeDto.getCareerDetails())
                .selfIntroduction(resumeDto.getSelfIntroduction())
                .build();
        this.resumeRepository.save(resume);
    }

    // [추가된 기능] 이력서 수정 메서드
    public void modify(Resume resume, ResumeDto resumeDto) {
        resume.setName(resumeDto.getName());
        resume.setPhone(resumeDto.getPhone());
        resume.setEmail(resumeDto.getEmail());
        resume.setTechStack(resumeDto.getTechStack());
        resume.setCareerDetails(resumeDto.getCareerDetails());
        resume.setSelfIntroduction(resumeDto.getSelfIntroduction());

        this.resumeRepository.save(resume);
    }

    // [추가된 기능] 이력서 삭제 메서드
    public void delete(Resume resume) {
        this.resumeRepository.delete(resume);
    }
}