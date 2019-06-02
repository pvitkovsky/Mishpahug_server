import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { EventDetail } from '../Models/index';
import { Observable, throwError } from 'rxjs';
import { AuthenticationService} from "./authentication.service"
import { map } from 'rxjs/operators';

@Injectable()
export class EventService {
    constructor(private http: HttpClient){

    }

    //TODO: all events, events by subscriber, subscribers by event; 
    //TODO: filter by date, holiday, confession, food; 

    getEvents(){
      const headers = new HttpHeaders({'Authorization': 'not null Authorization'});
      return this.http.get<EventDetail[]>("api/event/",{headers}) 
        map((res: EventDetail[]) => { 
              return res;
          }
        );
    }

    getEventsByOwner(ownerUserName : string){
      return this.http.get<EventDetail[]>("api/event/byowner/" + ownerUserName); 
        map((res: EventDetail[]) => { 
              return res;
          }
        );
    }

}
