import {Component, OnInit} from '@angular/core';
import {SidebarComponent} from "./sidebar.component";
import {ROUTER_DIRECTIVES, ROUTER_PROVIDERS} from "@angular/router-deprecated";
import {Host} from "../modules/host";
import {HostsService} from "../services/host.service";
import {LoginService} from "../services/login.service";
import {Router} from "@angular/router";

@Component({
    selector: 'my-dockers',
    templateUrl: 'src/app/templates/host.component.html',
    directives: [ROUTER_DIRECTIVES, SidebarComponent],
    providers: [ROUTER_PROVIDERS, HostsService, LoginService]
})
export class HostComponent implements OnInit {
    hosts: Host[];
    hostsForView: Host[][];
    currentPageNumber: number = 0;
    pageSum: number = 0;

    constructor(
        private _loginService: LoginService,
        private _router: Router,
        private _hostService: HostsService) { }

    getHosts() {
        this.hosts = this._hostService.getHosts();
    }

    getHostsForView(){
        var hostSum = this.hosts.length;
        if (hostSum < 10) {
            this.hostsForView = [this.hosts];
        }
        else {
            var res:Host[][] = [];
            var pages = Math.ceil(hostSum / 10);
            for (var i = 0; i < pages - 1; i++) {
                res.push(this.hosts.slice(i * 10, (i + 1) * 10));
            }
            var last = this.hosts.slice((pages - 1) * 10, hostSum);
            var fillLeng = pages * 10 - hostSum;
            for (var i = 0; i < fillLeng; i++){
                last.push(new Host("","",""));
            }
            res.push(last);
            this.hostsForView = res;
        }
    }

    ngOnInit() {
        // if (this._loginService.loginStatus() == false) {
        //     this._router.navigate(['/login']);
        //     return;
        // }
        this.getHosts();
        this.getHostsForView();
        this.currentPageNumber = 0;
        this.pageSum = this.hostsForView.length;
        console.log(this.hostsForView);
    }

    onNext() {
        if (this.currentPageNumber < this.pageSum - 1) {
            this.currentPageNumber += 1;
        }
        console.log(this.hostsForView);
        console.log(this.currentPageNumber);
    }

    onPre() {
        if (this.currentPageNumber > 0) {
            this.currentPageNumber -= 1;
        }
        console.log(this.currentPageNumber);
    }

    goBack() {
        window.history.back();
    }
}