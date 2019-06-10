import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { EventListComponent } from './eventlist.component';
import { EventsRendererComponent } from './eventsrenderer/eventsrenderer.component';
import { EventsByOwnerComponent } from './eventsbyowner/eventsbyowner.component';
import { EventsByGuestComponent } from './eventsbyguest/eventsbyguest.component';
import { EventsGeneralComponent } from './eventsgeneral/eventsgeneral.component';

@NgModule({
  declarations: [EventListComponent, EventsRendererComponent, EventsByOwnerComponent, EventsByGuestComponent, EventsGeneralComponent],
  imports: [
    CommonModule, 
    RouterModule
  ]
})
export class EventListModule { }

//TODO: events by.. and eventsrenderer are in the same space, but they are different. please solve
