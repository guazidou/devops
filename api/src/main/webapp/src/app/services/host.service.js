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
var host_mock_1 = require('../mocks/host.mock');
var HostsService = (function () {
    function HostsService() {
    }
    HostsService.prototype.getHosts = function () {
        return host_mock_1.HOSTS;
    };
    HostsService.prototype.getHost = function (ip) {
        return host_mock_1.HOSTS.filter(function (host) { return host.ip == ip; })[0];
    };
    HostsService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [])
    ], HostsService);
    return HostsService;
}());
exports.HostsService = HostsService;
//# sourceMappingURL=host.service.js.map