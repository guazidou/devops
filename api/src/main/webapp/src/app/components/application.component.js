"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var sidebar_component_1 = require("./sidebar.component");
var router_deprecated_1 = require("@angular/router-deprecated");
var router_1 = require("@angular/router");
var login_service_1 = require("../services/login.service");
var ApplicationComponent = (function () {
    function ApplicationComponent(_router, _loginService) {
        this._router = _router;
        this._loginService = _loginService;
    }
    ApplicationComponent.prototype.ngOnInit = function () {
        if (this._loginService.loginStatus() == false) {
            this._router.navigate(['/login']);
        }
    };
    ApplicationComponent.prototype.goBack = function () {
        window.history.back();
    };
    ApplicationComponent = __decorate([
        core_1.Component({
            selector: 'my-dockers',
            templateUrl: 'src/app/templates/application.component.html',
            directives: [router_deprecated_1.ROUTER_DIRECTIVES, sidebar_component_1.SidebarComponent],
            providers: [router_deprecated_1.ROUTER_PROVIDERS, login_service_1.LoginService]
        }), 
        __metadata('design:paramtypes', [router_1.Router, login_service_1.LoginService])
    ], ApplicationComponent);
    return ApplicationComponent;
}());
exports.ApplicationComponent = ApplicationComponent;
//# sourceMappingURL=application.component.js.map