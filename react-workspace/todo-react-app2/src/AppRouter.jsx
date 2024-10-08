import { Box, Typography } from "@mui/material";
import React from "react";
import App from "./App";
import Login from "./Login";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import SignUp from "./SignUp";
        //버전 이슈로 Switch대신 Routes로 대체
function Copyright() {
    return (
        <Typography variant="body2" color="textSecondary" align="center">
            {"copyright © "}
            fsoftwareengineer, {new Date().getFullYear()}
            {"."}
        </Typography>
    );
}

class AppRouter extends React.Component {
    render() {
        return (
            <Router>
                <Routes>
                    <Route path="/login" element={<Login />} />
                    <Route path="/" element={<App />} />
                    <Route path="/signup" element={<SignUp />} />
                </Routes>
                <Box mt={5}>
                    <Copyright />
                </Box>
            </Router>
        );
    }
}

export default AppRouter;
