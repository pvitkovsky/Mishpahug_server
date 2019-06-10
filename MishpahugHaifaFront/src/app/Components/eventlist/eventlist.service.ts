import { Injectable, OnInit } from '@angular/core';
import { EventService } from '../../Services/index';
import { UserService } from '../../Services/index';
import { UserDetail, EventDetail, EventFilter, EventStatus, EventConnection} from  '../../Models/index';
import { filter } from 'rxjs/operators'
import { switchMap, mergeMap } from 'rxjs/operators';
import { Observable } from "rxjs/Rx"

@Injectable({ 
	providedIn: 'root'
})

/*
* Static factory for the requests to EventController
*
*/
export class EventListService { // static factory of request parameters for the event service

	constructor(private eventService: EventService, private userService: UserService) {
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
		return new EventFilter(EventStatus.ALL, EventConnection.ALL);
	}

	private  getFilterOwner(userDetail : UserDetail) : EventFilter {
		return new EventFilter(EventStatus.ALL, EventConnection.OWNER, userDetail);
	}

	private getFilterGuest(userDetail : UserDetail) : EventFilter {
		return new EventFilter(EventStatus.ALL, EventConnection.GUEST, userDetail);
	}

	getEventsGeneral() : Observable<EventDetail[]> {
		return this.getEvents(this.getFilterGeneral());
	}

	getEventsOwner()  : Observable<EventDetail[]> {
    return this.userService.current().pipe(mergeMap(
      userDetail => this.getEvents(this.getFilterOwner(userDetail)).map(
        eventList => eventList
      ))
    )
  }


	getEventsGuest() : Observable<EventDetail[]> {
    return this.userService.current().pipe(mergeMap(
      userDetail => this.getEvents(this.getFilterGuest(userDetail)).map(
        eventList => eventList
      ))
    )
  }



}
