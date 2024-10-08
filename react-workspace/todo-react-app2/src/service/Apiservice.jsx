import { API_BASE_URL } from "../app-config";

export function call(api, method, request) {

    let headers = new Headers({
        "Content-Type": "application/json",
    })

    // 로컬스토리지에서 ACCESS_TOKEN 가져오기
    const accessToken = localStorage.getItem("ACCESS_TOKEN");
    if (accessToken && accessToken != null){
        headers.append("Authorization","Bearer " + accessToken)
    }


    let options = { // fetch에 매개변수 오브젝트 전달
        headers: headers,
        url: API_BASE_URL + api,
        method: method,
    };

 

    if (request) {
        // GET method가 아닌 경우에만 body를 추가
        options.body = JSON.stringify(request);
    }

    return fetch(options.url, options).then(async (response) => {
        const text = await response.text(); // 응답을 텍스트로 받음

        let json;
        try {
            json = text ? JSON.parse(text) : {}; // 응답이 비어있다면 빈 객체로 처리
        } catch (error) {
            return Promise.reject(new Error("Invalid JSON: " + error.message));
        }

        if (!response.ok) {
            console.log("URL:", options.url);
            if (response.status === 403) { // 403 상태 코드 확인
                window.location.href = "/login"; // redirect
            }
            return Promise.reject(json);
        }

        return json;
    }).catch((error) => {
        // 네트워크 오류 또는 기타 예외 처리
        console.log("Error:", error.message || error);
        return Promise.reject(error);
    });
}




export function signin(userDTO){
    return call("/auth/signin","POST",userDTO)
    .then((response) => {
        //성공화면 수정 후
        if(response.token){
            //로컬 스토리지에 토큰 저장
            localStorage.setItem("ACCESS_TOKEN",response.token);
            //token이 존재할 경우 Todo 화면으로 리디렉트
            window.location.href="/";
        }


        // 성공화면 수정 전
        // console.log("response : ",response);
        // alert("로그인 토큰" + response.token);
    });

}

export function signout(){
    localStorage.setItem("ACCESS_TOKEN",null);
    window.location.href="/login";
}


export function signup(userDTO){                       //회원가입
   return call("/auth/signup","POST",userDTO);
}