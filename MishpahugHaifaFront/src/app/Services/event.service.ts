import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {EventDetail, EventFilter, EVENTROOT} from '../Models/index';
import { Observable } from 'rxjs';

const eventConnectionString =  'api/event/';
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
      connectionString += filter.eventConnection.valueOf() + filter.appendUserDetail();
    }
    //console.log("connecting with connection string " + connectionString)
    var res : Observable<EventDetail[]> = this.http.get<EventDetail[]>(connectionString);
    //console.log("Request " + res);
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
    var body = {}; //TODO: disable stringifying of field guestID;
      for (var x in eventDetail) {
        if (x !== "guestsIds" ) {
          body[x] = this[x];
        }
      }

    var res : Observable<EventDetail> = this.http.put<EventDetail>(connectionString + eventDetail.id, body);
    return res;
  }
}
