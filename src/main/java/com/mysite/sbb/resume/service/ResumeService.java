package com.mysite.sbb.resume.service;

import com.mysite.sbb.resume.dto.ResumeDto;
import com.mysite.sbb.resume.entity.Resume;
import com.mysite.sbb.resume.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;

    // 이력서 목록 조회
    public List<Resume> getList() {
        // DB에 저장된 모든 이력서를 조회하여 반환합니다.
        return this.resumeRepository.findAll();
    }

    // 이력서 상세 조회
    public Resume getResume(Long id) {
        Optional<Resume> resume = this.resumeRepository.findById(id);
        if (resume.isPresent()) {
            return resume.get();
        } else {
            throw new RuntimeException("Resume not found"); // 해당 ID의 이력서가 없을 경우 예외 발생
        }
    }

    // 이력서 생성 및 저장
    public void create(ResumeDto resumeDto) {
        // DTO를 Entity로 변환하여 저장합니다.
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



}