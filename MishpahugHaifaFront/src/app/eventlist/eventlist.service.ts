import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {map} from 'rxjs/operators';

@Injectable()
export class EventlistService{
    constructor(private http: HttpClient){

    }
    getEvents(){
      const headers = new HttpHeaders({'Authorization': 'not null Authorization'});
      return this.http.get("http://localhost:8080/event/",{headers}).pipe(
        map((res: any) => {

              console.log(res);
              return res;
          }
        )
      );
}
}
