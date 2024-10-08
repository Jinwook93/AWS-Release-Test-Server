import React from 'react';
import { ListItem, ListItemText, Checkbox, InputBase, ListItemSecondaryAction, IconButton } from '@mui/material'; // MUI 컴포넌트 import
import { DeleteOutline } from '@mui/icons-material'; // MUI 아이콘 import

class Todo extends React.Component {
    constructor(props) {
        super(props);
        this.state = { item: props.item, readOnly: true };
        this.delete = props.delete;
        this.update = props.update;

    }


    deleteEventHandler = () => {

        this.delete(this.state.item);
    }


    enterKeyEventHandler = (e) => {

        if (e.key === "Enter") {
            this.setState({ readOnly: true });
            this.update(this.state.item);       //엔터를 누르면 저장
        }

    }


    checkboxEventHandler = (e) => {

        const thisItem = this.state.item;
        thisItem.done = !thisItem.done;
        this.setState({ item: thisItem });
        this.update(this.state.item);           //체크박스를 누르면 저장

    }

    // ReadOnly False Mode : 단순히 readOnly를 false로 바꿔주는 함수임
    offReadOnlyMode = () => {

        console.log("EVENT!", this.state.readOnly);
        this.setState({ readOnly: false }, () => {
        });
    }



    editEventHandler = (e) => {
        const thisItem = this.state.item;
        thisItem.title = e.target.value;
        this.setState({ item: thisItem });
        console.log("Edited item: ", this.state.item);
    }


    componentDidMount() {


      
        //============fetch에 매개변수 오브젝트 전달

        // options = {                 //fetch에 매개변수 오브젝트 전달

        //     method : "POST",
        //     headers : [
        //         ["Content-Type", "application/json"]
        //     ],
        //     body : JSON.stringify(data);
        // }

        // fetch("localhost:8081/todo",options)
        // .then(response => {
        //     //response 수신 시 하고 싶은 작업
        // })
        // .catch(e => {
        //     // 에러가 났을 때 하고 싶은 작업
        // })

          //=====================Promise를 이용한 XMLHttpRequest 사용====================
        // function exampleFunction(){
        //     return new Promise((resolve, reject) => {
        //         var oReq = new XMLHttpRequest();
        //         oReq.open("GET", "http://localhost:8081/todo");
        //         oReq.onload = function () {
        //             resolve(oReq.response);     //Resolve 상태
        //         };
        //         oReq.onerror = function () {
        //             reject(oReq.response);     //Reject 상태
        //         };
        //        oReq.send(); //pending 상태
        //     });
        // };

        // exampleFunction()
        // .then((r)=> console.log("Resolve " +r))
        // .catch((e) => console.log("Rejected " +e));

        //=====================XMLHttpRequest 사용====================
        // var oReq = new XMLHttpRequest();
        // oReq.open("GET","http://localhost:8081/todo");
        // oReq.onload = function () { //callback 함수 (인자로 넘겨 받는 함수를 의미)
        
        //     console.log(oReq.response);
        // }

        // oReq.send();

        // ==================fetch 사용 ================


        // const requestOptions = {
        //     method: "GET",
        //     headers: { "Content-Type": "application/json" },
        // }


        // fetch("http://localhost:8081/todo", requestOptions)
        //     .then((response) => response.json())
        //     .then(
        //         (response) => {
        //             this.setState({
        //                 items: response.data,
        //             });

        //         },
        //         (error) => {
        //             this.setState({
        //                 error,
        //             });
        //         }
        //     );
    };


    






    render() {
        const item = this.state.item;
        return (
            <ListItem>
                <Checkbox
                    checked={this.state.item.done} onChange={this.checkboxEventHandler} />
                <ListItemText>
                    <InputBase
                        inputProps={{
                            "aria-label": "naked",
                            readOnly: this.state.readOnly,

                        }}
                        onClick={this.offReadOnlyMode}
                        onKeyPress={this.enterKeyEventHandler}
                        onChange={this.editEventHandler}
                        type="text"
                        id={item.id}
                        name={item.id}
                        value={item.title}
                        multiline={true}
                        fullWidth={true}
                    />
                </ListItemText>

                <ListItemSecondaryAction>
                    <IconButton aria-label='Delete Todo' onClick={this.deleteEventHandler}>
                        <DeleteOutline />
                    </IconButton>
                </ListItemSecondaryAction>
            </ListItem>
        );
    }
}

export default Todo;









































// import React from 'react';
// import {ListItem, ListItemText, InputBase, Checkbox} from "@material-ui/core"
// class Todo extends React.Component {

//     constructor(props){
//         super(props);
//         this.state = {item: props.item};
//         }

//     render() {
//         return(
//             <ListItem>
//                 <ListItemText>
//             <div className="Todo">
//                 {/* <input type="checkbox" id="todo0" name="todo0" value="todo0" />
//                 <label for ="todo0">Todo 컴포넌트 만들기</label> */}

//                 {/* 자바스크립트 변수를 HTML 태그 내에서 사용하기/*/}
//              <input type="checkbox" id={this.state.item.id} name={this.state.item.id} checked= {this.state.item.done} />
//                 <label id = {this.state.item.id}>{this.state.item.title}</label>
//             </div>
//             </ListItemText>
//             </ListItem>
//         );

//     }
// }

// export default Todo;


//함수형

// import React from 'react';

// const Todo = (props) => {
//     const { item } = props;

//     return (
//         <div className="Todo">
//             {/* 자바스크립트 변수를 HTML 태그 내에서 사용하기 */}
//             <input type="checkbox" id={item.id} name={item.id} checked={item.done} />
//             <label htmlFor={item.id}>{item.title}</label>
//         </div>
//     );
// };

// export default Todo;
