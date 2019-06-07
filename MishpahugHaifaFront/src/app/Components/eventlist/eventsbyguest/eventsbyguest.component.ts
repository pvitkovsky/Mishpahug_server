import { Component, OnInit } from '@angular/core';
import { EventsRendererComponent } from '../eventsrenderer/eventsrenderer.component'
import { EventDetail } from '../../../Models/index';
import { EventListService } from '../eventlist.service'

@Component({
  selector: 'app-eventsbyguest',
  templateUrl: './eventsbyguest.component.html',
  styleUrls: ['./eventsbyguest.component.scss']
})
export class EventsByGuestComponent implements OnInit {
  
  eventList : EventDetail[]
  
  constructor(private eventListService : EventListService) { }

 ngOnInit() {
		this.eventListService.getEventsGuest().subscribe((res) => {

			res.subscribe(events =>  this.eventList = events);

		});
	}


}
