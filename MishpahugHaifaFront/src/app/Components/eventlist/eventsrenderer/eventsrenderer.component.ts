import { Component, Input, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { filter } from 'rxjs/operators'
import { EventDetail } from '../../../Models/index';
import { EventListService } from '../eventlist.service'

@Component({
	selector: 'app-eventsrenderer',
	templateUrl: './eventsrenderer.component.html',
	styleUrls: ['./eventsrenderer.component.scss']
})
export class EventsRendererComponent implements OnInit, OnChanges {

	@Input() childEventList : EventDetail[];
	events = [];
	keys = [];
	eventsExist: boolean = false;

	constructor() { }

 	ngOnInit(){
 		this.childEventList = [];
 	}

	ngOnChanges(changes: SimpleChanges) { 	
		if (changes['childEventList'] && this.childEventList !== undefined) { //TODO: something passes undefined. find out what
			this.keys = Object.keys(this.childEventList[0]); 
			for (let event in this.childEventList) {
				this.events.push(this.childEventList[event]);
			}
			this.eventsExist = true; 
		}
	}
}
