import {Component, OnInit} from '@angular/core';
import {ROUTER_DIRECTIVES} from "@angular/router";
import {ROUTER_PROVIDERS} from "@angular/router-deprecated";
import {SidebarComponent} from "./sidebar.component";

@Component({
    selector: 'my-dashboard',
    templateUrl: 'src/app/templates/dashboard.component.html',
    directives: [ROUTER_DIRECTIVES, SidebarComponent],
    providers: [ROUTER_PROVIDERS]
})
export class DashboardComponent implements OnInit {
    ngOnInit() {
    }

    goBack() {
        window.history.back();
    }
}