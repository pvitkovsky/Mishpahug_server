import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { ClarityModule } from 'clarity-angular';
import { MatSidenavModule, MatCheckboxModule } from '@angular/material';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule} from '@angular/material/card';

import { EventlistService} from './Components/eventlist/eventlist.service';
import { NavbarComponent } from './Components/navbar/navbar.component';
import { FooterComponent } from './Components/footer/footer.component';
import { EventlistComponent } from './Components/eventlist/eventlist.component';
import { CalendarlistComponent } from './Components/calendarlist/calendarlist.component';
import { HomeComponent } from './Components/home/home.component';
import { LoginComponent } from './Components/login/login.component';
import { RegisterComponent } from './Components/register/register.component';
import { ProfileComponent } from './Components/profile/profile.component';

import { AppComponent }  from './app.component';
import { MainRouter }        from './app.routing';

import { AlertComponent } from './Directives/index';
import { AuthGuard } from './Guards/index';
import { JwtInterceptor } from './Helpers/index';
import { AlertService, AuthenticationService, UserService, GuiService } from './Services/index';



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
        MatInputModule,
        MatCardModule
    ],
    declarations: [
        AppComponent,
        AlertComponent,
        NavbarComponent,
        FooterComponent,
        EventlistComponent,
        CalendarlistComponent,
        HomeComponent,
        LoginComponent,
        RegisterComponent,
        NavbarComponent,
        ProfileComponent
    ],
    providers: [
        EventlistService,
        AuthGuard,
        AlertService,
        AuthenticationService,
        UserService,
        {
            provide: HTTP_INTERCEPTORS,
            useClass: JwtInterceptor,
            multi: true
        },
    ],
    bootstrap: [AppComponent]
})

export class AppModule { }
