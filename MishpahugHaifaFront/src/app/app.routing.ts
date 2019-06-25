import { Routes, RouterModule } from '@angular/router';

import { EventListComponent } from './Components/event-list/event-list.component';
import { EventEditComponent } from './Components/event-details/event-edit/event-edit.component';

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
    component: EventListComponent
},
{
    path: 'edit/:id',
    component: EventEditComponent,
    canActivate: [AuthGuard]
},
{
    path: '**',
    redirectTo: ''
}
];

export const MainRouter = RouterModule.forRoot(appRoutes); //TODO: really not camelCase?
