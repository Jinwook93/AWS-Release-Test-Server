import React from "react";
import { signin } from "./service/Apiservice";
import { Button, Container, Grid, TextField, Typography } from "@mui/material";
import { Link } from "react-router-dom";

class Login extends React.Component {
constructor(props){
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this)
       // handleSubmit 메서드의 this를 현재 클래스 인스턴스에 바인딩
}

handleSubmit (event){
    event.preventDefault();
    const data = new FormData(event.target);
    const email = data.get("email");
    const password = data.get("password");
    // ApiService의 signin 메서드를 사용해 로그인
    signin ({email : email, password : password});
}


    render(){
        return (
            <Container component="main" maxWidth = "xs" style ={{marginTop : "8%"}}>
                <Grid container spacing ={2}>
                    <Grid item xs={12}>
                        <Typography component="h1" variant ="h5">
                            로그인
                        </Typography>
                    </Grid>
                </Grid>
                <form noValidate onSubmit ={this.handleSubmit}>
                    {"  "}
                    {/* submit 버튼을 클릭하면 handleSubmit이 실행됨 */}
                    <Grid container spacing ={2}>
                    <Grid item xs={12}>
                        <TextField
                        variant ="outlined"
                        required
                        fullWidth
                        id ="email"
                        label="이메일 주소"
                        name ="email"
                        autoComplete="email"
                        />
                    </Grid>
                         <Grid item xs={12}>
                        <TextField
                        variant ="outlined"
                        required
                        fullWidth
                        name ="password"
                        label="패스워드"
                        type = "password"
                        id ="password"
                        autoComplete="current-password"
                        />
                   
                    </Grid>
                    <Grid item xs={12}>
                       <Button 
                       type ="submit"
                       fullWidth
                       variant ="contained"
                       color ="primary">
                        로그인
                        </Button>
                    </Grid>
                <Link to ="/signup" variant ="body2">                    
                        <Grid item>계정이 없습니까? 여기서 가입하세요</Grid>
                    </Link>
                </Grid>

                </form>
            </Container>
        )
    }


}
export default Login;