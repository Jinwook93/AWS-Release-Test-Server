import { Container } from "@mui/system";
import { signup } from "./service/Apiservice";
import { Button, Grid, TextField, Typography } from "@mui/material";
import { Link } from "react-router-dom";
import React from "react";

class Signup extends React.Component {
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }


    handleSubmit(e) {
        e.preventDefault();
        //오브젝트에서 form에 저장된 데이터를 맵의 형태로 바꿔줌
        const data = new FormData(e.target);
        const username = data.get("username");
        const password = data.get("password");
        const email = data.get("email");
        signup({ email: email, username: username, password: password })
            .then((response) => {
                // 계정  생성 성공 시 login 페이지로 리디렉트
                window.location.href ="/login";
            });

    }

    render() {

        return (
            <Container component="main" maxWidth="xs" style={{ marginTop: "8%" }}>
                <form noValidate onSubmit={this.handleSubmit}>
                    <Grid container spacing={2}>
                        <Grid item xs={12}>
                            <Typography component="h1" variant="h5">
                                계정 생성
                            </Typography>
                        </Grid>
                        <Grid item xs={12}>
                            <TextField
                                autoComplete="fname"
                                name="username"
                                variant="outlined"
                                required
                                fullWidth
                                id="username"
                                label="사용자 이름"
                                autoFocus
                            />
                        </Grid>

                        <Grid item xs={12}>
                            <TextField
                                autoComplete="email"
                                name="email"
                                variant="outlined"
                                required
                                fullWidth
                                type="email"
                                id="email"
                                label="이메일 주소"  
                            />
                        </Grid>

                        <Grid item xs={12}>
                            <TextField
                                autoComplete="current-password"
                                name="password"
                                variant="outlined"
                                required
                                fullWidth
                                type="password"
                                id="password"
                                label="패스워드"
                            />
                        </Grid>
                        <Grid item xs={12}>
                        <Button type ="submit"
                        fullWidth
                        variant="contained"
                        color="primary"
                        >
                        계정 생성
                        </Button>    
                        </Grid>
                    </Grid>

                    <Grid container justify="flex-end">
                        <Grid item>
                            <Link to ="/login" variant ="body2">
                                    {/*   body는 Material-UI에서 기본적으로 제공하는 두 가지 "본문" 텍스트 스타일 중 하나입니다.
                              body1과 body2는 둘 다 본문 텍스트를 나타내며, body2는 body1에 비해 약간 작은 크기의 텍스트 스타일을 의미합니다.       
                                기본적으로, body2는 작은 크기의 본문 텍스트에 적합하며, 더 큰 본문 텍스트를 위해 body1을 사용할 수 있습니다. */}
                                이미 계정이 있습니까? 로그인 하세요.
                            </Link>
                        </Grid>
                    </Grid>
                </form>
            </Container>
        )
    }

}
export default Signup;