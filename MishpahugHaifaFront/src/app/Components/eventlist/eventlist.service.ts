import { Injectable, OnInit } from '@angular/core';
import { EventService } from '../../Services/index';
import { UserService } from '../../Services/index';
import { UserDetail, EventDetail, EventFilter, EventStatus, EventRelation } from  '../../Models/index';
import { filter } from 'rxjs/operators'
import { Observable } from "rxjs/Rx"

@Injectable({ 
	providedIn: 'root'
})

/*
* Static factory for the requests to EventController
*
*/
export class EventListService implements OnInit{ // static factory of request parameters for the event service

	loggedInUserId: number;
	loggedInUserName : string;
	loggedInUserDetail : UserDetail; 

	constructor(private eventService: EventService, private userService: UserService) { }


	ngOnInit(){ //TODO: won't work if not logged in - no UserDetail;
		this.userService.current().subscribe(
			userDetail => { 
//				console.log('eventlist service : current user id ' + userDetail.id)
				this.loggedInUserId = userDetail.id;
				this.loggedInUserName = userDetail.userName;
				this.loggedInUserDetail = userDetail;
			});
//		console.log("Arrived userDetail " + this.loggedInUserDetail)
	}

	private getEvents(eventFilter : EventFilter) : Observable<EventDetail[]>  {
		return this.eventService.getEvents(eventFilter)
		.pipe(filter(result => Object.keys(result).length  > 0)) // checking that result has keys;
		.map((response) => {
			//console.log( 'arrived into EventListService' + response )
			return response;
		});
	}

	private getFilterGeneral() : EventFilter {
		return new EventFilter(EventStatus.ALL, EventRelation.ALL);
	}

	private  getFilterOwner(userDetail : UserDetail) : EventFilter {
		return new EventFilter(EventStatus.ALL, EventRelation.OWNER, userDetail);
	}

	private getFilterGuest(userDetail : UserDetail) : EventFilter {
		return new EventFilter(EventStatus.ALL, EventRelation.GUEST, userDetail);
	}

	getEventsGeneral() : Observable<EventDetail[]> {
		return this.getEvents(this.getFilterGeneral());
	}

	getEventsOwner() : Observable<EventDetail[]> {
		return this.getEvents(this.getFilterOwner(this.loggedInUserDetail));
	}

	getEventsGuest() : Observable<EventDetail[]> {
		return this.getEvents(this.getFilterGuest(this.loggedInUserDetail));
	}



}
