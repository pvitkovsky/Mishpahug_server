import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SUBSCRIPTIONROOT} from '../Models/Connections/SubscriptionConnection';

@Injectable()
export class SubscriptionService {

  constructor(private http: HttpClient) { }

  subscribe(eventId : number, userId : number) : void {
    var connectionString : string = SUBSCRIPTIONROOT + '?eventid=' + eventId + '&userid=' + userId;
    console.log("connecting with connection string " + connectionString);
    var res = this.http.put(connectionString,{});
    res.subscribe(e=>console.log("Subscription activated " + JSON.stringify(e))); //TODO: needs to subscribe, or the request will not be fired;
  }

  unsubscribe(eventId : number, userId : number) : void {
    var connectionString : string = SUBSCRIPTIONROOT + '?eventid=' + eventId + '&userid=' + userId;
    console.log("connecting with connection string " + connectionString);
    var res = this.http.delete(connectionString);
    res.subscribe(e=>console.log("Subscription deactivated " + JSON.stringify(e))); //TODO: needs to subscribe, or the request will not be fired;
  }
}
