import { provideRouter, RouterConfig }  from '@angular/router';
import { DashboardComponent } from "../components/dashboard.component";
import { LoginComponent } from "../components/login.component";
import { DockerComponent } from "../components/docker.component";
import {HostComponent} from "../components/host.component";
import {ImageComponent} from "../components/image.component";
import {ApplicationComponent} from "../components/application.component";

const routes: RouterConfig = [
    {
        path: 'dashboard',
        component: DashboardComponent
    },
    // {
    //     path: 'detail/:id',
    //     component: HeroDetailComponent
    // },
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'dashboard/dockers',
        component: DockerComponent
    },
    {
        path: 'dashboard/hosts',
        component: HostComponent
    },
    {
        path: 'dashboard/images',
        component: ImageComponent
    },
    {
        path: 'dashboard/applications',
        component: ApplicationComponent
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