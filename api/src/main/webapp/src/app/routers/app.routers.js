"use strict";
var router_1 = require('@angular/router');
var dashboard_component_1 = require("../components/dashboard.component");
var login_component_1 = require("../components/login.component");
var docker_component_1 = require("../components/docker.component");
var host_component_1 = require("../components/host.component");
var image_component_1 = require("../components/image.component");
var application_component_1 = require("../components/application.component");
var routes = [
    {
        path: 'dashboard',
        component: dashboard_component_1.DashboardComponent
    },
    // {
    //     path: 'detail/:id',
    //     component: HeroDetailComponent
    // },
    {
        path: 'login',
        component: login_component_1.LoginComponent
    },
    {
        path: 'dashboard/dockers',
        component: docker_component_1.DockerComponent
    },
    {
        path: 'dashboard/hosts',
        component: host_component_1.HostComponent
    },
    {
        path: 'dashboard/images',
        component: image_component_1.ImageComponent
    },
    {
        path: 'dashboard/applications',
        component: application_component_1.ApplicationComponent
    },
    {
        path: '',
        redirectTo: '/login',
        pathMatch: 'full'
    },
];
exports.appRouterProviders = [
    router_1.provideRouter(routes)
];
//# sourceMappingURL=app.routers.js.map