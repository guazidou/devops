import { bootstrap }    from '@angular/platform-browser-dynamic';
import { IndexCompoent } from './app/index.component';
import { appRouterProviders } from './app/app.routers';

bootstrap(IndexCompoent, [appRouterProviders]);