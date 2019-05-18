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
  constructor(private EventServeice: EventlistService) { }

  ngOnInit() {
    this.EventServeice.getEvents().subscribe((response) => {
     this.keys = Object.keys(response[0]);
      for (let event in response) {
       for (let key in this.keys) {
              console.log(response[event][this.keys[key]]);
        }
        this.events.push(response[event]);
      }

    });
  }
}
