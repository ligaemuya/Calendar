import React, { useState, useEffect } from "react";
import { Calendar, Modal, Button, Form, Input, TimePicker, List, Radio} from "antd";
import koKR from 'antd/es/date-picker/locale/ko_KR';
import {fetchEvents, createEvent, updateEvent, deleteEvent}from "../services/calendarApi";
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../authContext/AuthContext';
import dayjs from 'dayjs';
import "dayjs/locale/ko";
dayjs.locale("ko");

const { TextArea } = Input;

const EventCalendar = () => {
    const [events, setEvents] = useState([]);
    const [isModalVisible, setIsModalVisible] = useState(false);
    const [selectedDate, setSelectedDate] = useState(null);
    const [selectedEvents, setSelectedEvents] = useState([]);
    const [eventForm] = Form.useForm();
    const [mode, setMode] = useState('month');
    const [isEditing, setIsEditing] = useState(false);
    const [currentEvent, setCurrentEvent] = useState(null);
    const navigate = useNavigate();
    const { state } = useAuth();

    useEffect(() => {
        loadEvents();
    }, []);

    const loadEvents = async () => {
        try {
            const data = await fetchEvents();
            setEvents(data);
        } catch (error) {
            console.error("Failed to load events", error);
        }
    };

    const handleSelect = (date, info) => {
        debugger;
        if (dayjs(date).isValid() && info.source === 'date') {
            const eventsForDate = events.filter(
                (event) => {
                    const isSameDate = dayjs(event.startTime).format("YYYY-MM-DD") === date.format("YYYY-MM-DD");
                    const isSharedOrOwned = event.share || event.userId === state.user.userId;
                    return isSameDate && isSharedOrOwned;
                }
            );
            setSelectedEvents(eventsForDate);
            setSelectedDate(date);
            setCurrentEvent(null);
            setIsEditing(false);
            eventForm.setFieldsValue({
                userId: state.user.userId,
                share: false
            });
            setIsModalVisible(true);
        }
    };

    const handleOk = async () => {
        try {
            const values = eventForm.getFieldsValue();
            const newEvent = {
                id: isEditing && currentEvent ? currentEvent.id : null,
                title: values.title,
                description: values.description,
                startTime: selectedDate
                    .hour(values.time.hour())
                    .minute(values.time.minute())
                    .second(0).format("YYYY-MM-DD HH:mm:ss"),
                endTime: selectedDate.set({
                    hour: values.time.hour() + 1,
                    minute: values.time.minute()
                }).format("YYYY-MM-DD HH:mm:ss"),
                userId: state.user.userId,
                share: values.share
            };
            if (isEditing && currentEvent) {
                await updateEvent(newEvent);
            } else {
                await createEvent(newEvent);
            }
            await loadEvents();
            setIsModalVisible(false);
            eventForm.resetFields();
        } catch (error) {
            console.error("Failed to add event", error);
        }
    };

    const handleDelete = async (eventId) => {
        try {
            await deleteEvent(eventId, fetchEvents);
            await loadEvents();
            const updatedEventsForDate = selectedEvents.filter(event => event.id !== eventId);
            setSelectedEvents(updatedEventsForDate);
            eventForm.resetFields();
            setIsEditing(false);
        } catch (error) {
            console.error("Failed to delete event", error);
        }
    };

    const handleCancel = () => {
        setIsModalVisible(false);
        eventForm.resetFields();
    };

    const handleEventClick = (event) => {
        debugger;
        if(event.userId === state.user.userId) {
            setCurrentEvent(event);
            eventForm.setFieldsValue({
                title: event.title,
                time: dayjs(event.startTime),
                description: event.description,
                userId: event.userId,
                share: event.share
            });
            setIsEditing(true);
            setIsModalVisible(true);
        }
    };

    const dateFullCellRender = (value) => {
        const listData = events.filter(
            (event) =>{
                const isSameDate = dayjs(event.startTime).format("YYYY-MM-DD") === value.format("YYYY-MM-DD");
                const isSharedOrOwned = event.share || event.userId === state.user.userId;
                return isSameDate && isSharedOrOwned;
            }
        );

        const isSunday = value.day() === 0;
        const cellStyle = isSunday ? { color: 'red'} : {};

        return (
            <div className="ant-picker-cell-inner ant-picker-calendar-date">
                <div className="ant-picker-calendar-date-value" style={cellStyle}>{value.date()}</div>
                <div className="ant-picker-calendar-date-content">
                    <ul>
                        {listData.map((event) => (
                            <li key={event.id}>
                                <strong>{event.title}</strong>
                            </li>
                        ))}
                    </ul>
                </div>
            </div>
        );
    };

    const handlePanelChange = (date, newMode) => {
        setMode(newMode);
    };

    return (
        <div style={{padding: "20px"}}>
            <h1>캘린더 일정 관리</h1>
            <Button type="default" onClick={() => navigate('/')}>로그아웃</Button>
            <Calendar
                locale={koKR}
                dateFullCellRender={dateFullCellRender}
                onSelect={handleSelect}
                onPanelChange={handlePanelChange}/>
            <Modal
                title={isEditing ? "일정 수정" : "일정 등록"}
                open={isModalVisible}
                onOk={handleOk}
                okText={isEditing ? "수정" : "등록"}
                onCancel={handleCancel}
                cancelText="취소"
            >
                <Form form={eventForm} layout="vertical">
                    <Form.Item name="title" label="제목" rules={[{required: true}]}>
                        <Input/>
                    </Form.Item>
                    <Form.Item name="time" label="시간" rules={[{required: true}]}>
                        <TimePicker format="HH:mm"/>
                    </Form.Item>
                    <Form.Item name="userId" label="작성자">
                        <Input disabled/>
                    </Form.Item>
                    <Form.Item name="share" label="일정공유">
                        <Radio.Group>
                            <Radio value={true}>공유</Radio>
                            <Radio value={false}>미공유</Radio>
                        </Radio.Group>
                    </Form.Item>
                    <Form.Item
                        name="description"
                        label="내용"
                        rules={[{required: true}]}
                    >
                        <TextArea rows={4}/>
                    </Form.Item>
                </Form>
                <List
                    dataSource={selectedEvents}
                    renderItem={item => (
                        <List.Item
                            actions={
                                item.userId === state.user.userId ?[
                                <Button type="link" danger onClick={() => handleDelete(item.id)}>삭제</Button>,
                                <Button type="link" onClick={() => handleEventClick(item)}>수정</Button>
                            ]: []}
                        >
                            <List.Item.Meta
                                title={<span>{item.title}</span>}
                                description={
                                    <span>
                                        {`${item.description} (${dayjs(item.startTime).format('HH:mm')})`}
                                        <br />작성자: {item.userId}
                                    </span>}
                            />
                        </List.Item>
                    )}
                />
            </Modal>
        </div>
    );
};

export default EventCalendar;