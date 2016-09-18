import { provideRouter, RouterConfig }  from '@angular/router';
import { HeroesComponent } from './heroes.component';
import { DashboardComponent } from "./dashboard.component";
import { HeroDetailComponent } from "./hero-detail.component";
import {LoginComponent} from "./login.component";

const routes: RouterConfig = [
    {
        path: 'heroes',
        component: HeroesComponent
    },
    {
        path: 'dashboard',
        component: DashboardComponent
    },
    {
        path: 'detail/:id',
        component: HeroDetailComponent
    },
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: '',
        redirectTo: '/login',
        pathMatch: 'full'
    },

];

export const appRouterProviders = [
    provideRouter(routes)
];