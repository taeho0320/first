package com.mysite.sbb.toolsurvey.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder @EntityListeners(AuditingEntityListener.class)
public class ToolSurvey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "toolsurvey_id")
    private Long id;

    @Column(nullable = false)
    private String nickname;

    private String preferredTool;

    private String teamMode;

    private String experience;

    private String note;

    @CreatedDate
    private LocalDateTime created;

}
