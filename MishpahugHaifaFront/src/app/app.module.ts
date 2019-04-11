import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { EventlistComponent } from './eventlist/eventlist.component';
import { CalendarlistComponent } from './calendarlist/calendarlist.component';
import {EventlistService} from './eventlist/eventlist.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HeaderComponent,
    FooterComponent,
    EventlistComponent,
    CalendarlistComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [EventlistService],
  bootstrap: [AppComponent]
})
export class AppModule { }
