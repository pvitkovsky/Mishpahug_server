import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SUBSCRIPTIONROOT} from '../Models/Connections/SubscriptionConnection';
import {UserDetail} from '../Models';
import {AuthenticationService} from './authentication.service';

@Injectable()
export class SubscriptionService {

  private currentUserId : number;

  constructor(private http: HttpClient, private authService: AuthenticationService) {
    this.authService.currentUser().subscribe(userDetail => this.currentUserId = userDetail.id);
  }

  subscribe(eventId : number) : void {
    var connectionString : string = SUBSCRIPTIONROOT + '?eventid=' + eventId + '&userid=' + this.currentUserId;
    var res = this.http.put(connectionString,{});
    res.subscribe(e=>console.log("Subscription activated " + JSON.stringify(e))); //TODO: needs to subscribe, or the request will not be fired;
  }

  unsubscribe(eventId : number) : void {
    var connectionString : string = SUBSCRIPTIONROOT + '?eventid=' + eventId + '&userid=' + this.currentUserId;
    var res = this.http.delete(connectionString);
    res.subscribe(e=>console.log("Subscription deactivated " + JSON.stringify(e))); //TODO: needs to subscribe, or the request will not be fired;
  }
}
