package com.mysite.sbb.resume.entity;

import com.mysite.sbb.audit.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Resume extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 이력서 ID

    @Column(length = 50, nullable = false)
    private String name; // 이름

    @Column(length = 20, nullable = false)
    private String phone; // 전화번호

    @Column(length = 100, nullable = false)
    private String email; // 이메일

    @Column(columnDefinition = "TEXT", nullable = false)
    private String techStack; // 주요 기술 스택

    @Column(columnDefinition = "TEXT", nullable = false)
    private String careerDetails; // 경력 사항

    @Column(columnDefinition = "TEXT", nullable = false)
    private String selfIntroduction; // 자기소개

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;
}