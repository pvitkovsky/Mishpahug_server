import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import { filter } from 'rxjs/operators'

import { UserService } from '../../Services/index';
import { UserDetail } from '../../Models/index';
import { EventService } from '../../Services/index';


@Component({
	selector: 'app-myeventlist',
	templateUrl: './myeventlist.component.html',
	styleUrls: ['./myeventlist.component.scss']
})
export class MyEventListComponent implements OnInit { //TODO: refactor into one component with EventList;

	@Input() array;
	@Output() output = new EventEmitter();

	events = [];
	keys = [];
	loggedInUserName : string;
	eventsExist: boolean = false;

	constructor(private eventService: EventService, private userService: UserService) { }

	ngOnInit() {
		this.userService.current().subscribe(
			userDetail => { //TODO: change subscribe into storing UserDetail on login; 
				this.loggedInUserName = userDetail.userName;
				this.eventService.getEventsByOwner(this.loggedInUserName)
				.pipe(filter(result => Object.keys(result).length >= 0)) // checking that result has keys;
				.subscribe(
					response => {
						this.keys = Object.keys(response[0]);
						for (let event in response) {
							this.events.push(response[event]);
						}
						this.eventsExist = true; 
					}, 
					error => {}      
					);
			},
			error =>{});
	}


}
