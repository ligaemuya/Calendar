import axios from "axios";

const calendarapi =  axios.create({
   baseURL: "http://localhost:8080/calendar/methods",
});

//조회 메서드
export const fetchEvents = async () => {
    try{
        const response = await calendarapi.get("/select");
        return response.data;
    } catch(error) {
        console.log(error);
    }
};

//생성 메서드
export const createEvent = async (data) => {
    try {
        const response = await calendarapi.post("/insert", data);
        return response.data;
    } catch(error) {
        console.log(error);
    }
};

//수정 메서드
export const updateEvent = async (data) => {
    try {
        const response = await calendarapi.put(`/update`, data);
        return response.data;
    } catch(error) {
        console.log(error);
    }
};

//삭제 메서드
export const deleteEvent = async (id, fetchEvents) => {
    try {
        const response = await calendarapi.delete(`/delete`, {data:{id:id}});
        fetchEvents();
        return response.data;
    } catch(error) {
        console.log(error);
    }
};
