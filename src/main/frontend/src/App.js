import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LoginForm from './components/LoginForm';
import SignUpForm from './components/SignUpForm';
import Calendar from "./components/Calendar";
import { AuthProvider } from './authContext/AuthContext';

import "antd/dist/reset.css"; // Ant Design CSS 리셋

const App = () => {
    return (
        <AuthProvider>
            <Router>
                <div>
                    <Routes>
                        <Route path="/" element={<LoginForm />} />
                        <Route path="/signup" element={<SignUpForm />}/>
                        <Route path="/home" element={<Calendar />} />
                    </Routes>
                </div>
            </Router>
        </AuthProvider>
    );
};

export default App;