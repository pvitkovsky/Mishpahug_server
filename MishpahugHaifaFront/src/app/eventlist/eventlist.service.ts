import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class EventlistService{
    constructor(private http: HttpClient){

    }
    getEvents(){
      return this.http.get("/event/");
}
}
