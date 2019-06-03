import { Component, OnInit } from '@angular/core';
import { EventsRendererComponent } from '../eventsrenderer/eventsrenderer.component'
import { EventDetail } from '../../../Models/index';
import { EventListService } from '../eventlist.service'

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
