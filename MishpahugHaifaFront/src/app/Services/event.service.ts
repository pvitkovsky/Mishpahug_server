import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {EventDetail, EventFilter, EVENTROOT, SUBSCRIPTIONROOT} from '../Models/index';
import { Observable } from 'rxjs';

@Injectable()
export class EventService {

  constructor(private http: HttpClient){
  }

  getEvents (filter? : EventFilter ) : Observable<EventDetail[]> {
    let connectionString : string;
    if(filter){
      connectionString = filter.getConnectionString();
    } else {
      connectionString = EVENTROOT;
    }
    var res : Observable<EventDetail[]> = this.http.get<EventDetail[]>(connectionString);
    return res;
  }

  getEvent(id : number) : Observable<EventDetail>{
    var connectionString : string = EVENTROOT;
    //console.log("connecting with connection string " + connectionString)
    var res : Observable<EventDetail> = this.http.get<EventDetail>(connectionString + id);
    //console.log("Request " + res);
    return res;
  }

  createEvent(eventDetail : EventDetail) : Observable<EventDetail> {
    var connectionString : string = EVENTROOT;
    console.log('eventDetail ' + JSON.stringify(eventDetail));
    var body = {}; //TODO: disable stringifying of field guestID;
    for (let x in eventDetail) {
      console.log('  property of E.D. ' + x.toString());
      console.log('  equals? ' + x.toString() !== 'guestIds');
      if (x.toString() !== "guestIds") {
        body[x] = eventDetail[x];
      }
    }
     console.log('post request with body ' + JSON.stringify(body));
    var res : Observable<EventDetail> = this.http.post<EventDetail>(connectionString, body);
      console.log('  returned' +  JSON.stringify(res));
    return res;
  }

  updateEvent(eventDetail : EventDetail) : Observable<EventDetail> {
    var connectionString : string = EVENTROOT;
    var body = {}; //TODO: disable stringifying of field guestID;
    for (let x in eventDetail) {
      if (x.toString() !== "guestIds") {
          body[x] = eventDetail[x];
      }
    }
    var res : Observable<EventDetail> = this.http.put<EventDetail>(connectionString + eventDetail.id, body);
    return res;
  }

  deleteEvent(eventDetail : EventDetail) : Observable<EventDetail> {
    var connectionString : string = EVENTROOT;
    var body = {}; //TODO: disable stringifying of field guestID;
    for (let x in eventDetail) {
      if (x.toString() !== "guestIds") {
        body[x] = eventDetail[x];
      }
    }
    var res : Observable<EventDetail> = this.http.delete<EventDetail>(connectionString + eventDetail.id, body);
    return res;
  }


}
