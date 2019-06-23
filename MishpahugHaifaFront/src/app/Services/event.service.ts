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

  updateEvent(eventDetail : EventDetail) : Observable<EventDetail> {
    var connectionString : string = EVENTROOT;
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


  // subscribe(eventId : number, userId : number) : void {
  //   var connectionString : string = "api/event/" //?eventid=1&userid=2/"  //SUBSCRIPTIONROOT + '?eventid=' + eventId + '&userid=' + userId;
  //   console.log("connecting with connection string " + connectionString);
  //   var res = this.http.get(connectionString);
  //   console.log(res.subscribe(e=>console.log(JSON.stringify(e))));
  // }
  //
  // unsubscribe(eventId : number, userId : number) : void {
  //   var connectionString : string = SUBSCRIPTIONROOT + '?eventid=' + eventId + '&userid=' + userId;
  //   console.log("connecting with connection string " + connectionString);
  //   this.http.delete(connectionString).map(
  //     (response) => {
  //       console.log(JSON.stringify(response));
  //     });
  // }
}
