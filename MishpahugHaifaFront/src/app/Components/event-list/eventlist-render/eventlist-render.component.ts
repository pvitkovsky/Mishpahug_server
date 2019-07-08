import { Component, Input, OnInit, OnChanges, SimpleChanges } from '@angular/core';
import { EventDetail } from '../../../Models/index';

@Component({
	selector: 'app-eventlistrenderer',
	templateUrl: './eventlist-render.component.html',
	styleUrls: ['./eventlist-render.component.scss']
})
export class EventListRenderComponent implements OnInit, OnChanges {

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
