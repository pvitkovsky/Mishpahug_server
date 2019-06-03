import { Routes, RouterModule } from '@angular/router';

import { EventListComponent } from './Components/eventlist/eventlist.component';

import { LoginComponent } from './Components/login/login.component';
import { RegisterComponent } from './Components/register/register.component';
import { ProfileComponent } from './Components/profile/profile.component';

import { AuthGuard } from './Guards/index';

const appRoutes: Routes = [{
        path: '',
        component: EventListComponent
    },
    {
        path: 'login',
        component: LoginComponent
    }, //TODO: into the dialog box;
    {
        path: 'register',
        component: RegisterComponent
    }, //TODO: into the dialog box;
    {
        path: 'user',
        canActivate: [AuthGuard],
        children: [{
            path: ':id',
            children: [{
                path: 'events', //TODO: better checking in the component; tied to the last segment...
                component: EventListComponent
            }, ]
        }, ]
    },
    { //TODO: children of user;
        path: 'profile',
        children: [{
                path: '',
                component: ProfileComponent,
                canActivate: [AuthGuard]
            }, 
            {
                path: ':id',
                component: ProfileComponent,
                canActivate: [AuthGuard]
            },
        ]
    },
    {
        path: 'eventlist',
        component: EventListComponent
    },
    {
        path: 'myeventlist',
        component: EventListComponent,
        canActivate: [AuthGuard]
    },
    {
        path: '**',
        redirectTo: ''
    }
];

export const MainRouter = RouterModule.forRoot(appRoutes); //TODO: really not camelCase?
