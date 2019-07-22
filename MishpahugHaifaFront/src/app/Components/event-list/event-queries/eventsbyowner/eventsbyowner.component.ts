import { Component, OnInit } from '@angular/core';
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
			this.eventList = res;
		});
	}

}
