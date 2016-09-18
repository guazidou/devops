import {Component, OnInit} from '@angular/core';
import {ROUTER_DIRECTIVES} from "@angular/router";
import {ROUTER_PROVIDERS} from "@angular/router-deprecated";

@Component({
    selector: 'sidebar',
    templateUrl: 'src/app/templates/sidebar.component.html',
    directives: [ROUTER_DIRECTIVES],
    providers: [ROUTER_PROVIDERS]
})
export class SidebarComponent implements OnInit {
    ngOnInit() {
    }
}