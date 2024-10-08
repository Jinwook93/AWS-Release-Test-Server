let backendHost;
const hostname = window && window.location && window.location.hostname;

if (hostname === "localhost"){
     backendHost = "http://localhost:8081"; 

}else{
   // backendHost = "http://prod-todo-backend.ap-northeast-2.elasticbeanstalk.com"  ; //자기 주소로 바꾸기 (기존)
    backendHost = "https://api.wookydev.com"  ; //수정
}

export const API_BASE_URL = `${backendHost}`;