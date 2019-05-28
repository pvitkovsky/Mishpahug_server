import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import { EventService } from '../../Services/index';

@Component({
  selector: 'app-eventlist',
  templateUrl: './eventlist.component.html',
  styleUrls: ['./eventlist.component.scss']
})
export class EventlistComponent implements OnInit {
  
  @Input() array;
  @Output() output = new EventEmitter();

  events = [];
  keys = [];
  
  constructor(private eventService: EventService) { }
  
  ngOnInit() {
    this.eventService.getEvents().subscribe((response) => {
     this.keys = Object.keys(response[0]);
      for (let event in response) {
        this.events.push(response[event]);
      }
    });
  }
}
