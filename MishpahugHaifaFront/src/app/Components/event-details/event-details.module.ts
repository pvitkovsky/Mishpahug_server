import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule }    from '@angular/forms';
import {EventEditComponent} from './event-edit/event-edit.component';
import {ParticipationComponent} from './participation/participation.component';
import {MatCardModule, MatInputModule, MatSelectModule} from '@angular/material';

@NgModule({
  declarations: [EventEditComponent, ParticipationComponent],
  imports: [
    FormsModule,
    CommonModule,
    MatCardModule,
    MatInputModule,
    MatSelectModule
  ]
})
export class EventDetailsModule { }
