import { Routes, RouterModule } from '@angular/router';

import { EventlistComponent } from './Components/eventlist/eventlist.component';
import { LoginComponent } from './Components/login/login.component';
import { RegisterComponent } from './Components/register/register.component';
import { AuthGuard } from './Guards/index';

const appRoutes: Routes = [
    { path: '', component: EventlistComponent, canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent }, //TODO: into the dialog box;
    { path: 'register', component: RegisterComponent }, //TODO: into the dialog box;
    { path: '**', redirectTo: '' }
];

export const MainRouter = RouterModule.forRoot(appRoutes); //TODO: really not camelCase?
