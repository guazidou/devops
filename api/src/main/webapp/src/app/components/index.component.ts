import { Component } from '@angular/core';
import { ROUTER_DIRECTIVES } from '@angular/router';
import {ROUTER_PROVIDERS} from "@angular/router-deprecated";
@Component({
    selector: 'index-app',
    templateUrl: 'src/app/templates/index.component.html',
    directives: [ROUTER_DIRECTIVES],
    providers: [ROUTER_PROVIDERS]
})
export class IndexComponent {
    title = '';
}