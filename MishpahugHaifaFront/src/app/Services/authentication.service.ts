import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

@Injectable()
export class AuthenticationService {
    constructor(private http: HttpClient) { }

    login(username: string, password: string) {
        return this.http.post<any>('api/user/login', { username: username, password: password })
            .map(loginResponse => {
            	  // console.log(loginResponse + ' and token ' + loginResponse.token);
                if (loginResponse && loginResponse.token) {
                    // console.log("login successful");
                    localStorage.setItem('currentUserToken', JSON.stringify(loginResponse));
                } else {
                    // console.log("login failed");
                }
                return loginResponse;
            });
    }

    logout() {
        localStorage.removeItem('currentUserToken');//TODO: logout on the backend
    }

    loggedIn() {
       if(localStorage.getItem('currentUserToken')){
           return true;
       }
       return false; 
    }

    currentUser(){  
        return this.http.get<any>('api/user/current')
            .map((user : any) => {
                return user;
            });
    }

}
