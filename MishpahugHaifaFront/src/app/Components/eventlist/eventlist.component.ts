import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {EventlistService} from './eventlist.service';

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
  constructor(private EventService: EventlistService) { }
  ngOnInit() {
    this.EventService.getEvents().subscribe((response) => {
     this.keys = Object.keys(response[0]);
      for (let event in response) {
        this.events.push(response[event]);
      }
    });
  }
}
