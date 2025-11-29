package com.mysite.sbb.toolsurvey.dto;

import com.mysite.sbb.toolsurvey.entity.ToolSurvey;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.aspectj.weaver.loadtime.definition.Definition;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ToolSurveyDto {

    @Size(min = 2, max = 15, message ="닉네임은 2~15자로 입력하세요.")
    @NotEmpty(message = "필수 입력 항목입니다.")
    private String nickname;

    @NotEmpty(message = "선호 개발 도구를 선택하세요.")
    private String preferredTool;

    @Size(min = 2, max = 20, message = "팀 프로젝트 방식을 2~20자로 입력하세요.")
    private String teamMode;

    @NotEmpty(message = "경험 수준을 선택하세요.")
    private String experience;

    @Size(max = 150, message = "비고는 최대 150자까지 입력할 수 있습니다.")
    @Column(columnDefinition = "TEXT")
    private String note;

    private LocalDateTime created;

    public ToolSurveyDto(ToolSurvey ts){
        this.nickname = ts.getNickname();
        this.note = ts.getNote();
        this.preferredTool = ts.getPreferredTool();
        this.experience = ts.getExperience();
        this.teamMode = ts.getTeamMode();
        this.created = ts.getCreated();
    }
}
