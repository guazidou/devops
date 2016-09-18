import {Component, OnInit} from '@angular/core';
import { ROUTER_DIRECTIVES } from '@angular/router';
import { HeroService }     from './hero.service';
import {Router} from "@angular/router";
@Component({
    selector: 'login-app',
    templateUrl: 'src/app/templates/login.component.html',
    directives: [ROUTER_DIRECTIVES],
    providers: [
        HeroService
    ]
})
export class LoginComponent  implements OnInit {
    title = 'Tour of Heroes';

    constructor(
        private router: Router) { }

    ngOnInit() {
    }
    
    onClickLogIn() {
        this.router.navigate(['/dashboard']);
    }
}