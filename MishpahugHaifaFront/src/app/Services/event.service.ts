import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
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

}
