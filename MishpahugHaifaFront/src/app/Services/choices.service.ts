import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ChoicesConnection } from '../Models/Connections/ChoicesConnection';



@Injectable({
	providedIn: 'root'
})
/*
* Service for getting the lists of available choose options;
*/
export class ChoicesService {


	constructor(private http: HttpClient){
	}

	getOptions (options: ChoicesConnection) : Observable<string[]> {
	  console.log('received choice ' + options.valueOf());
	  let res : Observable<string[]> = this.http.get<string[]>(options.valueOf());
    console.log('server answered ' + res)
		return res;
	}



}
