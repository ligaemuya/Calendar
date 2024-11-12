import axios from "axios";

const userApi =  axios.create({
    baseURL: "http://localhost:8080/user/methods",
});

export const signUp = async (signUpData) => {
    try {
        const response = await userApi.post('/signup', signUpData);
        return response.data; // "회원가입 성공" 메시지 반환
    } catch (error) {
        console.error("회원가입 오류: ", error);
        throw error;
    }
};

export const login = async (loginData) => {
    try {
        const response = await userApi.post('/login', loginData);
        return response.data; // SessionDto 반환
    } catch (error) {
        console.error("로그인 오류: ", error);
        throw error;
    }
};

export const checkUserId = async (userId) => {
    try {
        const response = await userApi.get('/checkUserId', {
            params: { userId },
        });
        return response.data; // CheckUserDto 반환
    } catch (error) {
        console.error("사용자 ID 확인 오류: ", error);
        throw error;
    }
};
