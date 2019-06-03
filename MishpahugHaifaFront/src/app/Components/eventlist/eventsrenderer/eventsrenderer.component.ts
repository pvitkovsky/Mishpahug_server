import { Component, Input, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { filter } from 'rxjs/operators'
import { EventDetail } from '../../../Models/index';
import { EventListService } from '../eventlist.service'

@Component({
	selector: 'app-eventsrenderer',
	templateUrl: './eventsrenderer.component.html',
	styleUrls: ['./eventsrenderer.component.scss']
})
export class EventsRendererComponent implements OnChanges {

	@Input() childEventList : EventDetail[];
	events = [];
	keys = [];
	eventsExist: boolean = false;

	constructor() { }


	ngOnChanges(changes: SimpleChanges) { //TODO: remove error on start
		// only run when property "data" changed
		if (changes['childEventList']) {
			this.keys = Object.keys(this.childEventList[0]); //TODO: DRY;
			for (let event in this.childEventList) {
				this.events.push(this.childEventList[event]);
			}
			this.eventsExist = true; 
		}
	}
}
