import React, { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Todo from './Todo'
import { Paper,List, Container, AppBar, Typography, Button, Toolbar, Grid } from '@mui/material'; 
import AddTodo from './AddTodo'
import { call, signout } from './service/Apiservice'


class App extends React.Component {
  // 클래스형 컴포넌트에서는 constructor를 사용하여 상태를 초기화합니다.
  constructor(props) {
    super(props);

  
    this.state = {
      items: [
      //   { id: 0, title: "Hello world 1", done: true },
      // { id: 1, title: "Hello world 2", done: false },
    ],
    // (1) 로딩 중이라는 상태를 표현할 변수 생성자에 상태 변수를 추가한다.
    loading : true,
    };
  }


componentDidMount() {
  // (2) componentDidMount에서 Todo 리스트를 가져오는 GET 요청이
  // 성공적으로 리턴하는 경우 loading을 false로 고친다 => 로딩 중이 더 이상 아니라는 뜻
  call("/todo","GET",null).then((response) =>
    this.setState({ items : response.data, loading :false})
    );
   
}



//1. add 함수 추가
  // add = (item) =>{
  //   const thisItems = this.state.items;
  //   item.id = "ID-" + thisItems.length; //key를 위한 id 추가
  //   item.done = false; //done 초기화
  //   thisItems.push(item); //리스트에 아이템 추가
  //   this.setState({items: thisItems}); //업데이트는 반드시 this.setState로 해야함
  //   console.log("items: ", this.state.items);


  // }


  add = (item) => {
    call("/todo","POST",item).then((response) =>
      this.setState({ items : response.data})
      );
  }



  //1. delete 함수 추가

  // delete = (item) =>{
  //   const thisItems = this.state.items;
  //   console.log("Before Update Item : ", this.state.items);
  //   const newItems = thisItems.filter (e => e.id !== item.id);    //'휴지통을 클릭한 대상만을 제외'한 Item들
  //   this.setState( {items: newItems}, ()  => {
  //   console.log("Update Items : ", this.state.items)

  // });

 delete = (item) => {
    call("/todo","DELETE",item).then((response) =>
      this.setState({ items : response.data})
      );
  }



// add = (item) =>{

//   const requestOptions = {
//       method: 'POST',
//       headers : {'Content-Type' : 'application/json'},
//       body : JSON.stringify(item)
//   };
//   fetch ('http://localhost:5000/todo', requestOptions)
//   .then(response => response.json())
//   .then(response => this.setState({item : response.data}));

// }

// delete = (item) => {
//   const requestOptions = {
//       method : 'DELETE',
//       headers : {'Content-Type' : 'application/json'},
//       body : JSON.stringify(item)
//   };
//   fetch ('http://localhost:5000/todo', requestOptions)
//   .then(response => response.json())
//   .then(response => this.setState({item : response.data}));


// }

    
// }

// update 추가
update = (item) => {
  call("/todo","PUT",item).then((response =>
    this.setState({ items : response.data})
    ));
} 


  render() {
    var todoItems = this.state.items.length > 0 && (
    <Paper style ={{margin:16}}>
      <List>
     { this.state.items.map((item, idx) => (
        <Todo key={item.id} item={item} delete ={this.delete} update = {this.update}/>
    ))}
      </List>

    </Paper>

    );

    //navigationBar 추가

    var navigationBar = (
      <AppBar position = "static">
        <Toolbar>
          <Grid justify = "space-between" container>
            <Grid item>
              <Typography variant ="h6" > 오늘의 할 일</Typography>
            </Grid>
            <Grid>
              <Button color ="inherit" onClick ={signout}>로그아웃</Button>
            </Grid>
          </Grid>
        </Toolbar>
      </AppBar>


    )

      /* 로딩 중이 아닐 때 렌더링할 부분 */
      var todoListPage = (
        <div>
              {navigationBar}  {/* 네비게이션 바 렌더링 */}
          <Container maxWidth ="md">
            <AddTodo add = {this.add}/>
            <div className="TodoList">
            {todoItems} {/* todoItems 배열을 직접 렌더링 */}

            </div> 
           </Container>
        </div>
      );


    // 로딩 중일 떄 렌더링 할 부분

    var loadingPage = <h1>로딩 중 ...</h1>

  var content = loadingPage;

  //로딩 중이 아닐 경우 todoListPage 선택
  if(!this.state.loading){    
    content = todoListPage;
  }



    return (
        <div className="App">
            {content}
        </div>
    );
}
}

export default App;























//============로딩 중 수정 전==============================


// import React, { useState } from 'react'
// import reactLogo from './assets/react.svg'
// import viteLogo from '/vite.svg'
// import './App.css'
// import Todo from './Todo'
// import { Paper,List, Container, AppBar, Typography, Button, Toolbar, Grid } from '@mui/material'; 
// import AddTodo from './AddTodo'
// import { call, signout } from './service/Apiservice'


// class App extends React.Component {
//   // 클래스형 컴포넌트에서는 constructor를 사용하여 상태를 초기화합니다.
//   constructor(props) {
//     super(props);

  
//     this.state = {
//       items: [
//       //   { id: 0, title: "Hello world 1", done: true },
//       // { id: 1, title: "Hello world 2", done: false },
//     ],
//     };
//   }


// componentDidMount() {
//   call("/todo","GET",null).then((response) =>
//     this.setState({ items : response.data})
//     );
   
// }



// //1. add 함수 추가
//   // add = (item) =>{
//   //   const thisItems = this.state.items;
//   //   item.id = "ID-" + thisItems.length; //key를 위한 id 추가
//   //   item.done = false; //done 초기화
//   //   thisItems.push(item); //리스트에 아이템 추가
//   //   this.setState({items: thisItems}); //업데이트는 반드시 this.setState로 해야함
//   //   console.log("items: ", this.state.items);


//   // }


//   add = (item) => {
//     call("/todo","POST",item).then((response) =>
//       this.setState({ items : response.data})
//       );
//   }



//   //1. delete 함수 추가

//   // delete = (item) =>{
//   //   const thisItems = this.state.items;
//   //   console.log("Before Update Item : ", this.state.items);
//   //   const newItems = thisItems.filter (e => e.id !== item.id);    //'휴지통을 클릭한 대상만을 제외'한 Item들
//   //   this.setState( {items: newItems}, ()  => {
//   //   console.log("Update Items : ", this.state.items)

//   // });

//  delete = (item) => {
//     call("/todo","DELETE",item).then((response) =>
//       this.setState({ items : response.data})
//       );
//   }



// // add = (item) =>{

// //   const requestOptions = {
// //       method: 'POST',
// //       headers : {'Content-Type' : 'application/json'},
// //       body : JSON.stringify(item)
// //   };
// //   fetch ('http://localhost:5000/todo', requestOptions)
// //   .then(response => response.json())
// //   .then(response => this.setState({item : response.data}));

// // }

// // delete = (item) => {
// //   const requestOptions = {
// //       method : 'DELETE',
// //       headers : {'Content-Type' : 'application/json'},
// //       body : JSON.stringify(item)
// //   };
// //   fetch ('http://localhost:5000/todo', requestOptions)
// //   .then(response => response.json())
// //   .then(response => this.setState({item : response.data}));


// // }

    
// // }

// // update 추가
// update = (item) => {
//   call("/todo","PUT",item).then((response =>
//     this.setState({ items : response.data})
//     ));
// } 


//   render() {
//     var todoItems = this.state.items.length > 0 && (
//     <Paper style ={{margin:16}}>
//       <List>
//      { this.state.items.map((item, idx) => (
//         <Todo key={item.id} item={item} delete ={this.delete} update = {this.update}/>
//     ))}
//       </List>

//     </Paper>

//     );

//     //navigationBar 추가

//     var navigationBar = (
//       <AppBar position = "static">
//         <Toolbar>
//           <Grid justify = "space-between" container>
//             <Grid item>
//               <Typography variant ="h6" > 오늘의 할 일</Typography>
//             </Grid>
//             <Grid>
//               <Button color ="inherit" onClick ={signout}>로그아웃</Button>
//             </Grid>
//           </Grid>
//         </Toolbar>
//       </AppBar>


//     )

//     // props로 넘겨주기
//     return (
//         <div className="App">
//           {navigationBar}  {/* 네비게이션 바 렌더링 */}
//           <Container maxWidth ="md">
//             <AddTodo add = {this.add}/>
//             <div className="TodoList">
//             {todoItems} {/* todoItems 배열을 직접 렌더링 */}

// </div> 
//            </Container>
//         </div>
//     );
// }
// }

// export default App;



















// class App extends React.Component {
//   // 클래스형 컴포넌트에서는 constructor를 사용하여 상태를 초기화합니다.
//   constructor(props) {
//     super(props);

//     // 1. item => items 배열로 바꾼다
//     this.state = {
//       items: [
//         { id: 0, title: "Hello world 1", done: true },
//       { id: 1, title: "Hello world 2", done: false },
//     ],
//     };
//   }

//   render() {
//     var todoItems = this.state.items.length > 0 &&
//     <Paper style ={{margin:16}}>
//       <List>
//      { this.state.items.map((item, idx) => (
//         <Todo key={item.id} item={item} />
//     ))}
//       </List>

//     </Paper>

  

//     // 3. 생성된 컴포넌트 리턴
//     return (
//         <div className="App">
//             {todoItems} {/* todoItems 배열을 직접 렌더링 */}
//         </div>
//     );
// }
// }

// export default App;

















// class App extends React.Component {
//   // 클래스형 컴포넌트에서는 constructor를 사용하여 상태를 초기화합니다.
//   constructor(props) {
//     super(props);

//     // 1. item => items 배열로 바꾼다
//     this.state = {
//       items: [
//         { id: 0, title: "Hello world 1", done: true },
//       { id: 1, title: "Hello world 2", done: false },
//     ],
//     };
//   }

//   render() {
//     // 2. 자바스크립트가 제공하는 map 함수를 이용해 배열을 반복해 <Todo ... /> 컴포넌트 생성
//     var todoItems = this.state.items.map((item, idx) => (
//         <Todo key={item.id} item={item} />
//     ));

//     // 3. 생성된 컴포넌트 리턴
//     return (
//         <div className="App">
//             {todoItems} {/* todoItems 배열을 직접 렌더링 */}
//         </div>
//     );
// }
// }

// export default App;




// class App extends React.Component {
//   // 클래스형 컴포넌트에서는 constructor를 사용하여 상태를 초기화합니다.
//   constructor(props) {
//     super(props);
//     this.state = {
//       item: { id: 0, title: "Hello world", done: true },
//     };
//   }

//   render() {
//     return (
//       <div className="App">
//         {/* Todo 컴포넌트에 state를 props로 전달합니다. */}
//         <Todo item={this.state.item} />
//       </div>
//     );
//   }
// }

// export default App;



// 함수형

// import { useState } from 'react'
// import './App.css'
// import Todo from './Todo'

// function App() {
//   // useState를 사용하여 상태를 초기화
//   const [item, setItem] = useState({ id: 0, title: "Hello world", done: true });

//   return (
//     <div className="App">
//       <Todo item={item} />
//     </div>
//   );
// }

// export default App;
