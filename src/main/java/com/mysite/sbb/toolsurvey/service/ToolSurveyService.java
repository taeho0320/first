package com.mysite.sbb.toolsurvey.service;

import com.mysite.sbb.question.dto.QuestionDto;
import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.toolsurvey.dto.ToolSurveyDto;
import com.mysite.sbb.toolsurvey.entity.ToolSurvey;
import com.mysite.sbb.toolsurvey.repository.ToolSurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ToolSurveyService {

    private final ToolSurveyRepository toolSurveyRepository;

    public void create(ToolSurveyDto toolSurveyDto) {
        ToolSurvey toolSurvey = ToolSurvey.builder()
                .nickname(toolSurveyDto.getNickname())
                .experience(toolSurveyDto.getExperience())
                .teamMode(toolSurveyDto.getTeamMode())
                .preferredTool(toolSurveyDto.getPreferredTool())
                .note(toolSurveyDto.getNote())
                .build();
        toolSurveyRepository.save(toolSurvey);

    }

    public List<ToolSurveyDto> getToolSurveyList() {
        List<ToolSurvey> TSList = toolSurveyRepository.findAll(Sort.by(Sort.Direction.DESC, "Id"));
        List<ToolSurveyDto> TSDtoList = new ArrayList<>();
        for(ToolSurvey ts : TSList) {
            ToolSurveyDto tsDto = new ToolSurveyDto(ts);
            TSDtoList.add(tsDto);
        }
        return TSDtoList;
    }
}
