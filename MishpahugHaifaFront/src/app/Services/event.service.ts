import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {EventDetail, EventFilter, EventRenderDetail, EVENTROOT, SUBSCRIPTIONROOT, UserDetail} from '../Models/index';
import { Observable } from 'rxjs';
import {AuthenticationService} from './authentication.service';

@Injectable()
export class EventService {

  private currentUserId : number;

  constructor(private http: HttpClient, private authService: AuthenticationService){
    this.authService.currentUser().subscribe(userDetail => this.currentUserId = userDetail.id);
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

  private getEvent(id : number) : Observable<EventDetail>{
    var connectionString : string = EVENTROOT;
    var res : Observable<EventDetail> = this.http.get<EventDetail>(connectionString + id);
    return res;
  }

  getRenderEvent(id: number) : Observable<EventRenderDetail>{
    return this.getEvent(id)
      .map(eventDetail => this.renderEvent(eventDetail));
  }

  createEvent(eventRender: EventRenderDetail) : Observable<EventRenderDetail> {
    var connectionString : string = EVENTROOT;
    return this.http.post<EventDetail>(connectionString, new EventDetail(eventRender))
      .map(eventDetail => this.renderEvent(eventDetail));
  }

  updateEvent(eventRender : EventRenderDetail) : Observable<EventRenderDetail> {
    var connectionString : string = EVENTROOT;
    return  this.http.put<EventDetail>(connectionString + eventRender.id, new EventDetail(eventRender))
      .map(eventDetail => this.renderEvent(eventDetail))
  }

  deleteEvent(eventRender : EventRenderDetail) : Observable<EventRenderDetail> {
    var connectionString : string = EVENTROOT;
    return this.http.delete<EventDetail>(connectionString + eventRender.id)
      .map((eventDetail : EventDetail) => this.renderEvent(eventDetail));
  }

  private renderEvent(eventDetail : EventDetail) : EventRenderDetail{
    console.log('rendering event ' + JSON.stringify(eventDetail));
    let canEdit : boolean = eventDetail.ownerId === this.currentUserId;
    let isSubscribed : boolean = eventDetail.guestIds.includes(this.currentUserId);
    console.log("can edit " + canEdit + " can subscribe " + isSubscribed);
    return new EventRenderDetail(eventDetail, canEdit, isSubscribed);
  }


}
