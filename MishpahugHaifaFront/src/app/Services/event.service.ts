import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { EventDetail, EventFilter } from '../Models/index';
import { Observable, throwError } from 'rxjs';
import { AuthenticationService} from "./authentication.service"
import { map } from 'rxjs/operators';

/*
* Fires requests to the EventController
*
*/
@Injectable()
export class EventService {
  constructor(private http: HttpClient){

  }

  getEvents (filter? : EventFilter ) : Observable<EventDetail[]> {
    var connectionString = 'api/event/'; 
    if(filter){
      connectionString += filter.eventRelation + filter.appendUserDetail();  // user details do not succeed in getting to the filter in time; 
    }
    //console.log("connecting with connection string " + connectionString)
    var res : Observable<EventDetail[]> = this.http.get<EventDetail[]>(connectionString);
    //console.log("Request " + res);
    return res;
  }

}
