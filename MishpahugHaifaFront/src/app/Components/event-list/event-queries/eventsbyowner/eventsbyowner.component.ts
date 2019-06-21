import { Component, OnInit } from '@angular/core';
import { EventRenderComponent } from '../../event-render/event-render.component'
import { EventDetail } from '../../../../Models';
import { EventListService } from '../../event-list.service'

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
