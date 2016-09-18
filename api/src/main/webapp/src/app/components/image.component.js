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
var ImageComponent = (function () {
    function ImageComponent(_loginService, _router) {
        this._loginService = _loginService;
        this._router = _router;
    }
    ImageComponent.prototype.ngOnInit = function () {
        if (this._loginService.loginStatus() == false) {
            this._router.navigate(['/login']);
            return;
        }
    };
    ImageComponent.prototype.goBack = function () {
        window.history.back();
    };
    ImageComponent = __decorate([
        core_1.Component({
            selector: 'my-dockers',
            templateUrl: 'src/app/templates/image.component.html',
            directives: [router_deprecated_1.ROUTER_DIRECTIVES, sidebar_component_1.SidebarComponent],
            providers: [router_deprecated_1.ROUTER_PROVIDERS, login_service_1.LoginService]
        }), 
        __metadata('design:paramtypes', [login_service_1.LoginService, router_1.Router])
    ], ImageComponent);
    return ImageComponent;
}());
exports.ImageComponent = ImageComponent;
//# sourceMappingURL=image.component.js.map