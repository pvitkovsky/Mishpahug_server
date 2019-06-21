import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { ClarityModule } from 'clarity-angular';
import { MatSidenavModule, MatCheckboxModule} from '@angular/material';
import {MatCardModule, MatInputModule, MatSelectModule} from '@angular/material';

import { NavbarComponent } from './Components/navbar/navbar.component';
import { EventListModule } from './Components/event-list/event-list.module';
import { EventDetailsModule } from './Components/event-details/event-details.module';
import { LoginComponent } from './Components/login/login.component';
import { RegisterComponent } from './Components/register/register.component';
import { ProfileComponent } from './Components/profile/profile.component';

import { AppComponent }  from './app.component';
import { MainRouter }        from './app.routing';

import { AlertComponent } from './Directives/index';
import { AuthGuard } from './Guards/index';
import { JwtInterceptor } from './Helpers/index';
import { AlertService, AuthenticationService, GuiService, UserService, EventService  } from './Services/index';




@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    NoopAnimationsModule,
    ClarityModule.forRoot(),
    MainRouter,
    MatSidenavModule,
    MatCheckboxModule,
    MatCardModule,
    MatInputModule,
    MatSelectModule,
    EventListModule,
    EventDetailsModule,
  ],
    declarations: [
        AppComponent,
        AlertComponent,
        NavbarComponent,
        LoginComponent,
        RegisterComponent,
        NavbarComponent,
        ProfileComponent,
    ],
    providers: [
        AuthGuard,
        AlertService,
        AuthenticationService,
        UserService,
        {
            provide: HTTP_INTERCEPTORS,
            useClass: JwtInterceptor,
            multi: true
        },
        EventService
    ],
    bootstrap: [AppComponent]
})

export class AppModule { }
