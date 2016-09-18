import { Injectable } from '@angular/core';
import { HOSTS } from '../mocks/host.mock';

@Injectable()
export class HostsService {
    getHosts() {
        return HOSTS;
    }

    getHost(ip: string) {
        return HOSTS.filter(host => host.ip == ip)[0];
    }
}
