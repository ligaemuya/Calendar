import React, { useState } from 'react';
import { Button, Form, Input, message } from 'antd';
import { useNavigate } from 'react-router-dom';
import { login } from '../services/userApi';
import { useAuth } from '../authContext/AuthContext';

const LoginForm = () => {
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();
    const { dispatch } = useAuth();

    const onFinish = async (values) => {
        setLoading(true);
        try {
            const response = await login(values);
            debugger;
            if (response.success) {
                message.success(response.message);
                dispatch({ type: 'LOGIN', payload: response });
                navigate('/home');
            } else {
                message.error(response.message);
            }
        } catch (error) {
            message.error('로그인 중 오류가 발생했습니다.');
        } finally {
            setLoading(false);
        }
    };

    const handleSignUpClick = () => {
        navigate('/signup');
    };

    return (
        <Form
            name="login"
            onFinish={onFinish}
            style={{ maxWidth: 300, margin: 'auto', padding: '100px 0' }}
        >
            <Form.Item
                name="userId"
                rules={[{ required: true, message: 'ID를 입력하세요' }]}
            >
                <Input placeholder="ID" />
            </Form.Item>

            <Form.Item
                name="password"
                rules={[{ required: true, message: '패스워드를 입력하세요' }]}
            >
                <Input.Password placeholder="패스워드" />
            </Form.Item>

            <Form.Item>
                <Button type="primary" htmlType="submit" loading={loading} block>
                    로그인
                </Button>
            </Form.Item>

            <Form.Item>
                <Button type="default" onClick={handleSignUpClick} block>
                    회원가입
                </Button>
            </Form.Item>
        </Form>
    );
};

export default LoginForm;