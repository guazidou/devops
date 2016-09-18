export class Host {
    ip: string;
    status: string;
    dockerVersion: string;

    constructor(ip: string, status: string, dockerVersion: string){
        this.ip = ip;
        this.status = status;
        this.dockerVersion = dockerVersion;
    }
}