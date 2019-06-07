import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { EventDetail, EventFilter } from '../Models/index';
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
    var connectionString = eventConnectionString; 
    if(filter){
      connectionString += filter.eventRelation + filter.appendUserDetail();  
    }
    //console.log("connecting with connection string " + connectionString)
    var res : Observable<EventDetail[]> = this.http.get<EventDetail[]>(connectionString);
    //console.log("Request " + res);
    return res;
  }

}
