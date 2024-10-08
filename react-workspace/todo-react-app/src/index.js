import React from 'react';  //리액트의 사용을 위해 import
import ReactDOM from 'react-dom/client';  //리액트 DOM의 사용을 위해
import './index.css';     //css 임포트
import App from './App';  //App 컴포넌트 임포트
import reportWebVitals from './reportWebVitals';

const root = ReactDOM.createRoot(document.getElementById('root'));//ReactDOM이 내부의 엘리먼트들을 'root' 엘리먼트에 render함
root.render(
  <React.StrictMode>
   <App />  {/*     App Component 사용법 */}
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
