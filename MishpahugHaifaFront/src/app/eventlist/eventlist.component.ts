import { Component, OnInit } from '@angular/core';
import {EventlistService} from './eventlist.service';

@Component({
  selector: 'app-eventlist',
  templateUrl: './eventlist.component.html',
  styleUrls: ['./eventlist.component.scss']
})
export class EventlistComponent implements OnInit {
  events = [];
  constructor(private EventServeice: EventlistService) { }

  ngOnInit() {
    this.EventServeice.getEvents().subscribe((response) => {
      console.log(response);
    });
  }

}
