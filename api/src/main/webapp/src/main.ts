import { bootstrap }    from '@angular/platform-browser-dynamic';
import { IndexComponent } from './app/components/index.component';
import { appRouterProviders } from './app/routers/app.routers';
import { HTTP_PROVIDERS } from '@angular/http';

bootstrap(IndexComponent, [appRouterProviders, HTTP_PROVIDERS]);