import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './Components/home/index';
import { LoginComponent } from './Components/login/index';
import { RegisterComponent } from './Components/register/index';
import { AuthGuard } from './Guards/index';

const appRoutes: Routes = [
    { path: '', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },

    // otherwise redirect to home
    { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes);
