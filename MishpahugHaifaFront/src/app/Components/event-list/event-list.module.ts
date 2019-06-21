import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { EventListComponent } from './event-list.component';
import { EventRenderComponent } from './event-render/event-render.component';
import { EventsByOwnerComponent } from './event-queries/eventsbyowner/eventsbyowner.component';
import { EventsByGuestComponent } from './event-queries/eventsbyguest/eventsbyguest.component';
import { EventsGeneralComponent } from './event-queries/eventsgeneral/eventsgeneral.component';

@NgModule({
  declarations: [EventListComponent, EventRenderComponent, EventsByOwnerComponent, EventsByGuestComponent, EventsGeneralComponent],
  imports: [
    CommonModule, 
    RouterModule
  ]
})
export class EventListModule { }

//TODO: events by.. and event-render are in the same space, but they are different. please solve
