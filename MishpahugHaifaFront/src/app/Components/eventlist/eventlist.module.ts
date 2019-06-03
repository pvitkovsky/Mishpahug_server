import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EventListComponent } from './eventlist.component';
import { EventsRendererComponent } from './eventsrenderer/eventsrenderer.component';
import { EventsByOwnerComponent } from './eventsbyowner/eventsbyowner.component';
import { EventsByGuestComponent } from './eventsbyguest/eventsbyguest.component';
import { EventsGeneralComponent } from './eventsgeneral/eventsgeneral.component';

@NgModule({
  declarations: [EventListComponent, EventsRendererComponent, EventsByOwnerComponent, EventsByGuestComponent, EventsGeneralComponent],
  imports: [
    CommonModule
  ]
})
export class EventListModule { }
