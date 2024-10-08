import React from 'react';
import { Paper, Button, Grid, TextField } from '@mui/material';


class AddTodo extends React.Component {
    // 클래스형 컴포넌트에서는 constructor를 사용하여 상태를 초기화합니다.
    constructor(props) {
        super(props);

        this.state = {
            item: { title: "" }
            
        };
        this.add =props.add; //prop의 함수를 this.add에 연결
    }

    // onInputChange 함수 작성

    onInputChange = (e) => {
        const thisItem = this.state.item;
        thisItem.title = e.target.value;
        this.setState({ item: thisItem });
        console.log(thisItem);
    }
  // onButtonClick 함수 작성
    onButtonClick = () =>{
        this.add(this.state.item);
        this.setState({item:{title:""}});
    }

   //enterKeyEventHandler 함수 작성

   enterKeyEventHandler = (e) =>{
    if(e.key === 'Enter'){
    this.onButtonClick();
    }
   }

  //함수 연결
    render() {
        return (
            <Paper style={{ margin: 16, padding: 16 }}>
                <Grid container>
                    <Grid xs={11} md={11} item style={{ paddingRight: 16 }}>

                        <TextField placeholder="Add Todo here" fullWidth
                      onChange={this.onInputChange} value={this.state.item.title} 
                      onKeyPress ={this.enterKeyEventHandler}/>
                    </Grid>

                    <Grid xs={11} md={11} item style={{ paddingRight: 16 }}>

                        <Button fullWidth color="secondary" variant="outlined"
                        onClick = {this.onButtonClick}>
                            +
                        </Button>
                    </Grid>


                </Grid>

            </Paper>
        );

    }
}
export default AddTodo;