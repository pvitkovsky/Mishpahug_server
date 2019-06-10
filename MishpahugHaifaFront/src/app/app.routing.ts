import { Routes, RouterModule } from '@angular/router';

import { EventListComponent } from './Components/eventlist/eventlist.component';
import { EventsGeneralComponent } from './Components/eventlist/eventsgeneral/eventsgeneral.component';
import { EventsByOwnerComponent } from './Components/eventlist/eventsbyowner/eventsbyowner.component';
import { EventDetailsComponent } from './Components/event-details/event-details.component';

import { LoginComponent } from './Components/login/login.component';
import { RegisterComponent } from './Components/register/register.component';
import { ProfileComponent } from './Components/profile/profile.component';

import { AuthGuard } from './Guards/index';

const appRoutes: Routes = [{
    path: '',
    component: EventListComponent // Allow-all EventList
},
{
    path: 'login',
    component: LoginComponent
}, 
{
    path: 'register',
    component: RegisterComponent
}, 
{ 
    path: 'profile',
    children: [{
        path: '**',  //TODO: redirect '' onto Profile and everything else where?
        component: ProfileComponent,
        canActivate: [AuthGuard]
    },
    {
        path: ':id',
        component: ProfileComponent,
        canActivate: [AuthGuard]
    }]
},
{
    path: 'events',
    component: EventListComponent //TODO: guarded and unguarded component based on eventparams
},
{
    path: 'detail/:id',
    component: EventDetailsComponent,
    canActivate: [AuthGuard]
},
{
    path: '**',
    redirectTo: ''
}
];

export const MainRouter = RouterModule.forRoot(appRoutes); //TODO: really not camelCase?
