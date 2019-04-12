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
  keys;
  constructor(private EventServeice: EventlistService) { }

  ngOnInit() {
    this.EventServeice.getEvents().subscribe((response: Response) => {
      this.events = response.body;
      this.keys = Object.keys(this.events[0]);
      console.log(this.keys);
      for (let event in this.events) {
        for (let key in this.keys) {
          if (event[key] == undefined){
            event[key] = "";
          }
        }
      }
      for (let responseKey in response) {
        console.log(response[responseKey]);
      }

    });
  }
}
