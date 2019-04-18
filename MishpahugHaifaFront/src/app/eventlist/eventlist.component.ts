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
      //console.log(response);
     this.keys = Object.keys(response[0]);
      console.log(this.keys);
      for (let event in response) {
        //console.log(response[event]);
       for (let key in this.keys) {
              //console.log(this.keys[key]);
              //console.log(response[event][this.keys[key]]);
        }
        console.log(response[event]);
        console.log(response[event][this.keys[7]][0]);
        this.events.push(response[event]);
      }

    });
  }
}
