import { Component, OnInit } from '@angular/core';
import { EventDetail } from '../../../../Models/index';
import { EventListService } from '../../event-list.service'

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

			 this.eventList = res;

		});
	}


}
