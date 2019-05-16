import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { EventlistService} from './Components/eventlist/eventlist.service';
import { HeaderComponent } from './Components/header/header.component';
import { FooterComponent } from './Components/footer/footer.component';
import { EventlistComponent } from './Components/eventlist/eventlist.component';
import { CalendarlistComponent } from './Components/calendarlist/calendarlist.component';
import { HomeComponent } from './Components/home/index';
import { LoginComponent } from './Components/login/index';
import { RegisterComponent } from './Components/register/index';

import { AppComponent }  from './app.component';
import { routing }        from './app.routing';

import { AlertComponent } from './Directives/index';
import { AuthGuard } from './Guards/index';
import { JwtInterceptor } from './Helpers/index';
import { AlertService, AuthenticationService, UserService } from './Services/index';


@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule,
        routing
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
        RegisterComponent
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
