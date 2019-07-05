import {EventEmitter, Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import {UserDetail} from '../Models';
import {tap} from 'rxjs/internal/operators/tap';
import {first, flatMap} from 'rxjs/internal/operators';
import {ReplaySubject} from 'rxjs';
import {getMatIconFailedToSanitizeLiteralError} from '@angular/material';

@Injectable()
export class AuthenticationService {

    private loginEmitter: EventEmitter<boolean> = new EventEmitter();
    private currentUserEmitter: ReplaySubject<UserDetail> = new ReplaySubject(1);
    //TODO: make this subject emit the last value consistently. Now it gives just the first client?

    constructor(private http: HttpClient) { }

    login(username: string, password: string) : void {
      this.http.post<any>('api/user/login', {username: username, password: password}).pipe(
        tap(loginResponse => {
          if (loginResponse && loginResponse.token) {
            localStorage.setItem('currentUserToken', JSON.stringify(loginResponse));
            this.loginEmitter.next(true);
          }
        }),
        flatMap(() => this.http.get<any>('api/user/current')))
        .subscribe((user : UserDetail) => {
          console.log(" pushing next CurrentUser ")
          this.currentUserEmitter.next(user);
        });
    }

    logout() {
      localStorage.removeItem('currentUserToken');//TODO: logout on the backend
      this.loginEmitter.next(false);
    }

    loggedIn() : Observable<boolean> {
       return this.loginEmitter;
    }

    currentUser() : Observable<UserDetail> { //TODO: make currentUserEmitter work;
      return this.http.get<any>('api/user/current')
        .map((user : any) => {
          return user;
        });
    }


}
