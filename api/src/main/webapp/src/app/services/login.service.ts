import { Injectable } from '@angular/core';

var isLogin = false;
var loginUser = "";
var loginToken = "";
@Injectable()
export class LoginService {

    login(username: string, password: string) {
        if (username == "SystemAdmin" && password == "Chinapex007"){
            loginUser = "SystemAdmin";
            loginToken = "abcdefg12345678";
            isLogin = true;
            return true;
        }
        return false;
    }

    logout() {
        isLogin = false;
    }

    loginStatus() {
        return isLogin;
    }

}