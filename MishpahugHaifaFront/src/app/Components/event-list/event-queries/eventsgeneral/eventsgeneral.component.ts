import { Component, OnInit } from '@angular/core';
import { EventDetail } from '../../../../Models';
import { EventListService } from '../../event-list.service'

@Component({
  selector: 'app-eventsgeneral',
  templateUrl: './eventsgeneral.component.html',
  styleUrls: ['./eventsgeneral.component.scss'],
})
export class EventsGeneralComponent implements OnInit {

  eventList : EventDetail[]
  
  constructor(private eventListService : EventListService) { }

  ngOnInit() {
  	this.eventListService.getEventsGeneral().subscribe((res) => {
  		this.eventList = res;
  	});
  	
  }

}
