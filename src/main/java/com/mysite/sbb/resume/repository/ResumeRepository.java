package com.mysite.sbb.resume.repository;

import com.mysite.sbb.resume.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    // findAll(), save(), findById() 등 기본 메서드는 JpaRepository에서 자동 제공됩니다.
}