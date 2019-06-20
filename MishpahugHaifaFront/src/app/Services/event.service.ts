import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {EventDetail, EventFilter, EVENTROOT} from '../Models/index';
import { Observable } from 'rxjs';

/*
* Fires requests to the EventController
*
*/
@Injectable()
export class EventService {

  constructor(private http: HttpClient){
  }

  getEvents (filter? : EventFilter ) : Observable<EventDetail[]> {
    var connectionString : string = EVENTROOT;
    if(filter){
      connectionString += filter.combineConnectionString();
    }
    var res : Observable<EventDetail[]> = this.http.get<EventDetail[]>(connectionString);
    return res;
  }

  getEvent(id : number) : Observable<EventDetail>{
    var connectionString : string = "api/event/"
    //console.log("connecting with connection string " + connectionString)
    var res : Observable<EventDetail> = this.http.get<EventDetail>(connectionString + id);
    //console.log("Request " + res);
    return res;
  }

  updateEvent(eventDetail : EventDetail) : Observable<EventDetail> {
    var connectionString : string = "api/event/"
    // console.log('eventDetail ' + JSON.stringify(eventDetail));
    var body = {}; //TODO: disable stringifying of field guestID;
    for (let x in eventDetail) {
      // console.log('  property of E.D. ' + x.toString());
      // console.log('  equals? ' + x.toString() !== 'guestIds');
      if (x.toString() !== "guestIds") {
          body[x] = eventDetail[x];
      }
    }
    // console.log('put request with body ' + JSON.stringify(body));
    var res : Observable<EventDetail> = this.http.put<EventDetail>(connectionString + eventDetail.id, body);
      //  console.log('  returned' +  JSON.stringify(res));
    return res;
  }
}
