"use strict";
var Host = (function () {
    function Host(ip, status, dockerVersion) {
        this.ip = ip;
        this.status = status;
        this.dockerVersion = dockerVersion;
    }
    return Host;
}());
exports.Host = Host;
//# sourceMappingURL=host.js.map