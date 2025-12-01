package com.mysite.sbb.resume.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResumeDto {

    // 이력서 페이지에 필요한 항목들입니다.

    @NotEmpty(message = "이름은 필수 항목입니다.")
    @Size(max = 50, message = "이름은 50자 이하로 작성해 주세요.")
    private String name; // 이름

    @NotEmpty(message = "전화번호는 필수 항목입니다.")
    @Size(max = 20, message = "전화번호는 20자 이하로 작성해 주세요.")
    private String phone; // 전화번호

    @NotEmpty(message = "이메일은 필수 항목입니다.")
    @Size(max = 100, message = "이메일은 100자 이하로 작성해 주세요.")
    private String email; // 이메일

    @NotEmpty(message = "주요 기술 스택은 필수 항목입니다.")
    private String techStack; // 주요 기술 스택 (예: Java, Spring Boot, JPA)

    @NotEmpty(message = "경력 사항은 필수 항목입니다.")
    private String careerDetails; // 경력 사항

    @NotEmpty(message = "자기소개는 필수 항목입니다.")
    private String selfIntroduction; // 자기소개
}