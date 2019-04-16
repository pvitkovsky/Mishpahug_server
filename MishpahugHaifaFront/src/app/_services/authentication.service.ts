import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map'

@Injectable()
export class AuthenticationService {
    constructor(private http: HttpClient) { }

    login(username: string, password: string) {
        console.log("LocalStorage -> begin -> " + localStorage.getItem('currentUser'));
        return this.http.post<any>('http://localhost:8080/user/login', { username: username, password: password })
            .map(user => {
                console.log("Login -> " + user.token);
                if (user && user.token) {
                    localStorage.setItem('currentUser', JSON.stringify(user));
                }
                console.log("LocalStorage -> end -> " + localStorage.getItem('currentUser'));
                return user;
            });
    }
    logout() {
        console.log("LocalStorage -> begin -> LogOut -> " + localStorage.getItem('currentUser'));
        // remove user from local storage to log user out
        const headers = new HttpHeaders({'Authorization': JSON.stringify(localStorage.getItem('currentUser'))});
        this.http.post<any>('http://localhost:8080/user/logout', null,{headers});
        localStorage.removeItem('currentUser');
    }
}