package com.reactProject.exam.backend.controller;

import com.reactProject.exam.backend.Entity.CalendarEntity;
import com.reactProject.exam.backend.dto.CalendarDto;
import com.reactProject.exam.backend.service.CalendarService;
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

    @PostMapping("/insert")
    public CalendarDto createEvent(@RequestBody CalendarDto calendarDto) {
        return calendarService.saveEvent(calendarDto);
    }

    @GetMapping("/select")
    public List<CalendarDto> getAllEvents() {
        return calendarService.getAllEvents();
    }

    @PutMapping("/update")
    public CalendarDto updateEvent(@RequestBody CalendarDto calendarDto) {
        return calendarService.updateEvent(calendarDto.getId(), calendarDto);
    }

    @DeleteMapping("/delete")
    public void deleteEvent(@RequestBody CalendarDto calendarDto) {
        calendarService.deleteEvent(calendarDto.getId());
    }

}
