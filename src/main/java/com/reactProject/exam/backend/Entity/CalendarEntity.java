package com.reactProject.exam.backend.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.reactProject.exam.backend.dto.CalendarDto;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "calendar")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalendarEntity {

    @Id
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "start_time", nullable = false)
    private Timestamp startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "end_time", nullable = false)
    private Timestamp endTime;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(nullable = false)
    private boolean share;

    public CalendarDto toDto() {
        CalendarDto dto = new CalendarDto();
        dto.setId(this.id);
        dto.setTitle(this.title);
        dto.setDescription(this.description);
        dto.setStartTime(this.startTime);
        dto.setEndTime(this.endTime);
        dto.setUserId(this.userId);
        dto.setShare(this.share);
        return dto;
    }
}