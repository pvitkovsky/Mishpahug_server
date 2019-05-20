import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MatSidenavModule, MatCheckboxModule } from '@angular/material';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { ClarityModule } from 'clarity-angular';

import { EventlistService} from './Components/eventlist/eventlist.service';
import { HeaderComponent } from './Components/header/header.component';
import { FooterComponent } from './Components/footer/footer.component';
import { EventlistComponent } from './Components/eventlist/eventlist.component';
import { CalendarlistComponent } from './Components/calendarlist/calendarlist.component';
import { HomeComponent } from './Components/home/index';
import { LoginComponent } from './Components/login/index';
import { RegisterComponent } from './Components/register/index';
import { SidenavComponent } from './Components/sidenav/index';

import { AppComponent }  from './app.component';
import { MainRouter }        from './app.routing';

import { AlertComponent } from './Directives/index';
import { AuthGuard } from './Guards/index';
import { JwtInterceptor } from './Helpers/index';
import { AlertService, AuthenticationService, UserService } from './Services/index';



@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule,
        NoopAnimationsModule,
        MatSidenavModule,
        MatCheckboxModule,
        ClarityModule.forRoot(),
        MainRouter,
    ],
    declarations: [
        AppComponent,
        AlertComponent,
        HeaderComponent,
        FooterComponent,
        EventlistComponent,
        CalendarlistComponent,
        HomeComponent,
        LoginComponent,
        RegisterComponent,
        SidenavComponent
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
