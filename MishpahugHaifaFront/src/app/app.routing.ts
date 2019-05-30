import { Routes, RouterModule } from '@angular/router';

import { EventlistComponent } from './Components/eventlist/eventlist.component';
import { LoginComponent } from './Components/login/login.component';
import { RegisterComponent } from './Components/register/register.component';
import { ProfileComponent } from './Components/profile/profile.component';

import { AuthGuard } from './Guards/index';

const appRoutes: Routes = [
    { path: '', component: EventlistComponent, canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent }, //TODO: into the dialog box;
    { path: 'register', component: RegisterComponent }, //TODO: into the dialog box;
    { path: 'profile', children: [ // TODO: redirect into my profile
      { path: '', component: ProfileComponent, canActivate: [AuthGuard]  },
    //  { path: ':id', component: ProfileComponent, canActivate: [AuthGuard]  }, // TODO: query server for other profile
    ]},
    { path: '**', redirectTo: '' }
];

export const MainRouter = RouterModule.forRoot(appRoutes); //TODO: really not camelCase?
