import {Component, OnInit} from '@angular/core';
import { ROUTER_DIRECTIVES } from '@angular/router';
import {Router} from "@angular/router";
import {LoginService} from "../services/login.service";
@Component({
    selector: 'login-app',
    templateUrl: 'src/app/templates/login.component.html',
    directives: [ROUTER_DIRECTIVES],
    providers:[LoginService]
})
export class LoginComponent  implements OnInit {
    username = "";
    password = "";
    constructor(
        private _loginService: LoginService,
        private router: Router) { }

    ngOnInit() {
    }
    
    onClickLogIn() {
        if (this._loginService.login(this.username, this.password)){
            this.router.navigate(['/dashboard/hosts']);
        }
    }
}