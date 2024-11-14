package com.reactProject.exam.backend.controller;

import com.reactProject.exam.backend.Entity.CalendarEntity;
import com.reactProject.exam.backend.dto.CalendarDto;
import com.reactProject.exam.backend.service.CalendarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/calendar/methods")
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    @Operation(summary = "일정 생성", description = "새로운 캘린더 일정 생성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "일정 생성 성공", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CalendarDto.class)) }),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/insert")
    public CalendarDto createEvent(@Parameter(description = "생성할 이벤트 정보") @RequestBody CalendarDto calendarDto) {
        return calendarService.saveEvent(calendarDto);
    }

    @Operation(summary = "전체 일정 조회", description = "모든 일정을 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이벤트 조회 성공", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = List.class)) }),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/select")
    public List<CalendarDto> getAllEvents() {
        return calendarService.getAllEvents();
    }

    @Operation(summary = "일정 수정", description = "기존 일정 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수정 성공", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CalendarDto.class)) }),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "404", description = "이벤트를 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PutMapping("/update")
    public CalendarDto updateEvent(@Parameter(description = "업데이트할 일정 정보") @RequestBody CalendarDto calendarDto) {
        return calendarService.updateEvent(calendarDto.getId(), calendarDto);
    }

    @Operation(summary = "일정 삭제", description = "기존 일정을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "삭제 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "404", description = "일정을 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @DeleteMapping("/delete")
    public void deleteEvent(@RequestBody CalendarDto calendarDto) {
        calendarService.deleteEvent(calendarDto.getId());
    }

}
