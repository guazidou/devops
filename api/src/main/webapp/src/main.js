"use strict";
var platform_browser_dynamic_1 = require('@angular/platform-browser-dynamic');
var index_component_1 = require('./app/components/index.component');
var app_routers_1 = require('./app/routers/app.routers');
var http_1 = require('@angular/http');
platform_browser_dynamic_1.bootstrap(index_component_1.IndexComponent, [app_routers_1.appRouterProviders, http_1.HTTP_PROVIDERS]);
//# sourceMappingURL=main.js.map