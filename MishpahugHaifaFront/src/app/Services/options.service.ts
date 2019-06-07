import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const optionsConnectionStrings = { religions : 'api/religion', maritals : 'api/meritalstatus', kitchens : 'api/kichentype', genders : 'api/gender'};

@Injectable({
	providedIn: 'root'
})
/*
* Service for getting the lists of available choose options;
*/
export class OptionsService {

	constructor(private http: HttpClient){
	}

	getReligions ( ) : Observable<String[]> {
		return this.http.get<String[]>(optionsConnectionStrings.religions);
	}

		getMaritals ( ) : Observable<String[]> {
		return this.http.get<String[]>(optionsConnectionStrings.maritals);
	}

		getKitchens ( ) : Observable<String[]> {
		return this.http.get<String[]>(optionsConnectionStrings.kitchens);
	}

		getGenders ( ) : Observable<String[]> {
		return this.http.get<String[]>(optionsConnectionStrings.genders);
	}

}
