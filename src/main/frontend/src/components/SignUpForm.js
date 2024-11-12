import React, { useState } from 'react';
import { Button, Form, Input, message, Space } from 'antd';
import { useNavigate } from 'react-router-dom';
import { signUp, checkUserId } from '../services/userApi';

const SignUpForm = () => {
    const [form] = Form.useForm();
    const [loading, setLoading] = useState(false);
    const [isCheckingId, setIsCheckingId] = useState(false);
    const [isUserIdAvailable, setIsUserIdAvailable] = useState(null);
    const navigate = useNavigate();

    const onFinish = async (values) => {
        if (isUserIdAvailable === false) {
            message.error('사용할 수 없는 사용자 ID입니다. 다른 ID를 입력하세요.');
            return;
        }
        setLoading(true);
        try {
            const signUpData = {
                userId: values.userId,
                username: values.username,
                password: values.password,
            };
            const response = await signUp(signUpData);
            message.success(response);  // "회원가입 성공" 메시지 처리
            navigate('/');  // 가입 성공 후 로그인 페이지로 이동
        } catch (error) {
            message.error('회원가입 중 오류가 발생했습니다.');
        } finally {
            setLoading(false);
        }
    };

    const handleCheckUserId = async () => {
        const userId = form.getFieldValue('userId');
        if (!userId) {
            message.warning('ID를 입력하세요.');
            return;
        }

        setIsCheckingId(true);
        try {
            const result = await checkUserId(userId);
            debugger;
            setIsUserIdAvailable(!result.success);
            if (result.success) {
                message.error('이미 사용 중인 ID입니다. 다른 ID를 입력하세요.');
            } else {
                message.success('사용 가능한 ID입니다.');
            }
        } catch (error) {
            console.error("사용자 ID 확인 오류: ", error);
            message.error("사용자 ID 확인 중 오류가 발생했습니다.");
        } finally {
            setIsCheckingId(false);
        }
    };

    return (
        <Form
            form={form}
            name="signup"
            onFinish={onFinish}
            style={{ maxWidth: 300, margin: 'auto', padding: '100px 0' }}
        >
            <Form.Item
                name="username"
                rules={[{ required: true, message: '이름을 입력하세요' }]}
            >
                <Input placeholder="이름" />
            </Form.Item>

            <Form.Item
                name="userId"
                rules={[{ required: true, message: 'ID를 입력하세요' }]}
            >
                <Space.Compact style={{ display: 'flex' }}>
                    <Input
                        style={{ width: 'calc(100% - 100px)' }}
                        placeholder="ID"
                    />
                    <Button
                        type="primary"
                        loading={isCheckingId}
                        onClick={handleCheckUserId}
                    >
                        중복 확인
                    </Button>
                </Space.Compact>
            </Form.Item>

            <Form.Item
                name="password"
                rules={[{ required: true, message: '패스워드를 입력하세요' }]}
                hasFeedback
            >
                <Input.Password placeholder="패스워드" />
            </Form.Item>

            <Form.Item
                name="confirmPassword"
                dependencies={['password']}
                hasFeedback
                rules={[
                    { required: true, message: '패스워드를 확인하세요' },
                    ({ getFieldValue }) => ({
                        validator(_, value) {
                            if (!value || getFieldValue('password') === value) {
                                return Promise.resolve();
                            }
                            return Promise.reject(new Error('패스워드가 일치하지 않습니다.'));
                        },
                    }),
                ]}
            >
                <Input.Password placeholder="패스워드 확인" />
            </Form.Item>

            <Form.Item>
                <Button type="primary" htmlType="submit" loading={loading} block>
                    회원가입
                </Button>
            </Form.Item>
            <Form.Item>
                <Button type="default" onClick={() => navigate('/')} block>
                    로그인 화면으로 돌아가기
                </Button>
            </Form.Item>
        </Form>
    );
};

export default SignUpForm;