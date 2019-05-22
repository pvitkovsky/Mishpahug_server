import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

@Injectable()
export class AuthenticationService {
    constructor(private http: HttpClient) { }

    login(username: string, password: string) {
        return this.http.post<any>('api/user/login', { username: username, password: password })
            .map(user => {
            	//TODO: JSONServer fake backend ii to skip login
                if (user && user.token) {
                    console.log("login successful");
                    localStorage.setItem('currentUserToken', JSON.stringify(user));
                } else {
                    console.log("login failed");

                }

                return user;
            });
    }

    logout() {
        localStorage.removeItem('currentUserToken');//TODO: logout on the backend
    }

    loggedIn() {
       if(this.currentUser()){
           return true;
       }
       return false; 
    }

    currentUser(){ //TODO: make this return a user object, maybe from user service. definitely not a token. 
        return localStorage.getItem('currentUserToken');
    }

}
