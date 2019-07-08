import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule }    from '@angular/forms';
import {EventEditComponent} from './event-edit/event-edit.component';
import {ParticipationComponent} from './participation/participation.component';
import {MatCardModule, MatInputModule, MatSelectModule} from '@angular/material';
import { EventRenderComponent } from './event-edit/event-render/event-render.component';

@NgModule({
  declarations: [EventEditComponent, ParticipationComponent, EventRenderComponent],
  imports: [
    FormsModule,
    CommonModule,
    MatCardModule,
    MatInputModule,
    MatSelectModule
  ]
})
export class EventDetailsModule { }
