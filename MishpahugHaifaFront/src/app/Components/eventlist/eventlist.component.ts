import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import { filter } from 'rxjs/operators'
import { EventService } from '../../Services/index';

@Component({
  selector: 'app-eventlist',
  templateUrl: './eventlist.component.html',
  styleUrls: ['./eventlist.component.scss']
})
export class EventListComponent implements OnInit {

  
  @Input() array;
  @Output() output = new EventEmitter();

  events = [];
  keys = [];
  
  constructor(private eventService: EventService) { }
  
  ngOnInit() {
    this.eventService.getEvents()
    .pipe(filter(result => Object.keys(result).length  >= 0)) // checking that result has keys;
    .subscribe((response) => {
      this.keys = Object.keys(response[0]);
      for (let event in response) {
        this.events.push(response[event]);
      }
    });
  }

}
