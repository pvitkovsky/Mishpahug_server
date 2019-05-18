import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {map} from 'rxjs/operators';

@Injectable()
export class EventlistService{
    constructor(private http: HttpClient){

    }
    getEvents(){
      const headers = new HttpHeaders({'Authorization': 'not null Authorization'});
      return this.http.get("api/event/",{headers}).pipe(
        map((res: any) => {
              return res;
          }
        )
      );
}
}
