import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {SUBSCRIPTIONROOT} from '../Models/Connections/SubscriptionConnection';
import {UserDetail} from '../Models';
import {AuthenticationService} from './authentication.service';
import {map} from 'rxjs/operators';

@Injectable()
export class SubscriptionService {

  private currentUserId : number;

  constructor(private http: HttpClient, private authService: AuthenticationService) {
    this.authService.currentUser().subscribe(userDetail => this.currentUserId = userDetail.id);
  }

  sub(eventId : number) : Observable<boolean> {
    var connectionString : string = SUBSCRIPTIONROOT + '?eventid=' + eventId + '&userid=' + this.currentUserId;
    var res = this.http.put(connectionString,{});
    return res.pipe(
      map(e => true)
    );
  }

  unsub(eventId : number) : Observable<boolean> {
    var connectionString : string = SUBSCRIPTIONROOT + '?eventid=' + eventId + '&userid=' + this.currentUserId;
    var res = this.http.delete(connectionString);
      return res.pipe(
      map(e => true)
    );
  }
}
