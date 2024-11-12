package com.reactProject.exam.backend.service;
import com.reactProject.exam.backend.Entity.CalendarEntity;
import com.reactProject.exam.backend.dto.CalendarDto;
import com.reactProject.exam.backend.exception.ResourceNotFoundException;
import com.reactProject.exam.backend.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CalendarService {
    @Autowired
    private CalendarRepository calendarRepository;

    public String createUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 일정등록
     *
     * @param calendarDto
     * @return
     */
    public CalendarDto saveEvent(CalendarDto calendarDto) {
        //1. 일정등록
        CalendarEntity calendarEntity = new CalendarEntity();
        calendarEntity.setId(createUUID());
        calendarEntity.setTitle(calendarDto.getTitle());
        calendarEntity.setDescription(calendarDto.getDescription());
        calendarEntity.setStartTime(calendarDto.getStartTime());
        calendarEntity.setEndTime(calendarDto.getEndTime());
        calendarEntity.setShare(calendarDto.isShare());
        calendarEntity.setUserId(calendarDto.getUserId());
        calendarEntity = calendarRepository.save(calendarEntity);
        return calendarEntity.toDto();
    }

    /**
     * 전체일정조회
     *
     * @return List<CalendarEntity>
     */
    public List<CalendarDto> getAllEvents() {
        //1. 전체일정조회
        List<CalendarEntity> calendarEntityList = calendarRepository.findAll();
        return calendarEntityList.stream().map(CalendarEntity::toDto).toList();
    }

    /**
     * 일정 수정
     *
     * @param id
     * @param calendarDto
     * @return
     */
    public CalendarDto updateEvent(String id, CalendarDto calendarDto) {
        // 1. 주어진 id로 기존 일정조회
        Optional<CalendarEntity> selectEntity = calendarRepository.findById(id);

        // 2. 수정객체 검증 존재하지 않는 경우, 예외 호출
        if (selectEntity.isEmpty()) {
            throw new ResourceNotFoundException("일정이 존재하지않습니다! : " + id);
        }

        CalendarEntity existingEntity = selectEntity.get();

        // 3. 매개변수 entity랑 매핑
        existingEntity.setTitle(calendarDto.getTitle());
        existingEntity.setDescription(calendarDto.getDescription());
        existingEntity.setStartTime(calendarDto.getStartTime());
        existingEntity.setEndTime(calendarDto.getEndTime());
        existingEntity.setEndTime(calendarDto.getEndTime());
        existingEntity.setShare(calendarDto.isShare());

        // 4. 일정 수정
        existingEntity = calendarRepository.save(existingEntity);
        return existingEntity.toDto();
    }

    /**
     * 일정 삭제
     * @param id
     */
    public void deleteEvent(String id) {
        //1.id로 일정삭제
        calendarRepository.deleteById(id);
    }

}
