import { Component } from '@angular/core';
import { ROUTER_DIRECTIVES } from '@angular/router';
import { HeroService }     from './hero.service';
@Component({
    selector: 'index-app',
    template: `
    <router-outlet></router-outlet>
    `,
    directives: [ROUTER_DIRECTIVES],
    providers: [
        HeroService
    ]
})
export class IndexCompoent {
    title = 'Tour of Heroes';
}