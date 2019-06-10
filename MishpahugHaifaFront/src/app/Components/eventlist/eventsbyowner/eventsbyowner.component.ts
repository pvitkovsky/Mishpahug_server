import { Component, OnInit } from '@angular/core';
import { EventsRendererComponent } from '../eventsrenderer/eventsrenderer.component'
import { EventDetail } from '../../../Models/index';
import { EventListService } from '../eventlist.service'

@Component({
	selector: 'app-eventsbyowner',
	templateUrl: './eventsbyowner.component.html',
	styleUrls: ['./eventsbyowner.component.scss']
})
export class EventsByOwnerComponent implements OnInit {

	eventList : EventDetail[]

	constructor(private eventListService : EventListService) { }

	ngOnInit() {
		this.eventListService.getEventsOwner().subscribe((res) => {
		//	console.log("Owner component events " + res);
			this.eventList = res;
		//	console.log("General component events " + this.eventList)
		});
	}

}
