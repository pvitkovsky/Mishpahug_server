import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UserDetail } from '../Models/index';
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
      return this.http.get("api/event/",{headers}) 
        map((res: any) => {
              return res;
          }
        );
    }

    // getEventsByGuest(UserDetail guest){      
    //   return this.http.get("api/event/?" + ).pipe( 
    //     map((res: any) => {
    //           return res;
    //       }
    //     )
    //   );
    // }

}
