import {Component, OnInit} from '@angular/core';
import {SidebarComponent} from "./sidebar.component";
import {ROUTER_DIRECTIVES, ROUTER_PROVIDERS} from "@angular/router-deprecated";
import {LoginService} from "../services/login.service";
import {Router} from "@angular/router";

@Component({
    selector: 'my-dockers',
    templateUrl: 'src/app/templates/docker.component.html',
    directives: [ROUTER_DIRECTIVES, SidebarComponent],
    providers: [ROUTER_PROVIDERS, LoginService]
})
export class DockerComponent implements OnInit {
    constructor(
        private _loginService: LoginService,
        private _router: Router) { }

    ngOnInit() {
        if (this._loginService.loginStatus() == false) {
            this._router.navigate(['/login']);
            return;
        }
    }

    goBack() {
        window.history.back();
    }
}