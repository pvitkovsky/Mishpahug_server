import { Component, OnInit } from '@angular/core';
import { EventRenderComponent } from '../../eventlist-render/event-render.component'
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
  		//console.log("General component events " + res)
  		this.eventList = res;
  		//console.log("General component events " + this.eventList)
  	});
  	
  }

}
