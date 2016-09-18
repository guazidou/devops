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
var host_1 = require("../modules/host");
var host_service_1 = require("../services/host.service");
var login_service_1 = require("../services/login.service");
var router_1 = require("@angular/router");
var HostComponent = (function () {
    function HostComponent(_loginService, _router, _hostService) {
        this._loginService = _loginService;
        this._router = _router;
        this._hostService = _hostService;
        this.currentPageNumber = 0;
        this.pageSum = 0;
    }
    HostComponent.prototype.getHosts = function () {
        this.hosts = this._hostService.getHosts();
    };
    HostComponent.prototype.getHostsForView = function () {
        var hostSum = this.hosts.length;
        if (hostSum < 10) {
            this.hostsForView = [this.hosts];
        }
        else {
            var res = [];
            var pages = Math.ceil(hostSum / 10);
            for (var i = 0; i < pages - 1; i++) {
                res.push(this.hosts.slice(i * 10, (i + 1) * 10));
            }
            var last = this.hosts.slice((pages - 1) * 10, hostSum);
            var fillLeng = pages * 10 - hostSum;
            for (var i = 0; i < fillLeng; i++) {
                last.push(new host_1.Host("", "", ""));
            }
            res.push(last);
            this.hostsForView = res;
        }
    };
    HostComponent.prototype.ngOnInit = function () {
        // if (this._loginService.loginStatus() == false) {
        //     this._router.navigate(['/login']);
        //     return;
        // }
        this.getHosts();
        this.getHostsForView();
        this.currentPageNumber = 0;
        this.pageSum = this.hostsForView.length;
        console.log(this.hostsForView);
    };
    HostComponent.prototype.onNext = function () {
        if (this.currentPageNumber < this.pageSum - 1) {
            this.currentPageNumber += 1;
        }
        console.log(this.hostsForView);
        console.log(this.currentPageNumber);
    };
    HostComponent.prototype.onPre = function () {
        if (this.currentPageNumber > 0) {
            this.currentPageNumber -= 1;
        }
        console.log(this.currentPageNumber);
    };
    HostComponent.prototype.goBack = function () {
        window.history.back();
    };
    HostComponent = __decorate([
        core_1.Component({
            selector: 'my-dockers',
            templateUrl: 'src/app/templates/host.component.html',
            directives: [router_deprecated_1.ROUTER_DIRECTIVES, sidebar_component_1.SidebarComponent],
            providers: [router_deprecated_1.ROUTER_PROVIDERS, host_service_1.HostsService, login_service_1.LoginService]
        }), 
        __metadata('design:paramtypes', [login_service_1.LoginService, router_1.Router, host_service_1.HostsService])
    ], HostComponent);
    return HostComponent;
}());
exports.HostComponent = HostComponent;
//# sourceMappingURL=host.component.js.map