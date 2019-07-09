import {EventEmitter, Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import {UserDetail} from '../Models';
import {tap} from 'rxjs/internal/operators/tap';
import {first, flatMap, map, takeLast} from 'rxjs/internal/operators';
import {BehaviorSubject, ReplaySubject} from 'rxjs';
import {getMatIconFailedToSanitizeLiteralError} from '@angular/material';

@Injectable()
export class AuthenticationService {

    private currentUserDetail = JSON.parse(localStorage.getItem('currentUserDetail')) ;
    private currentUserEmitter: BehaviorSubject<UserDetail> =
        new BehaviorSubject(this.currentUserDetail);     //Can I send an error if the user is not initialised?

    constructor(private http: HttpClient) {
      this.currentUserEmitter.subscribe(user => localStorage.setItem('currentUserDetail', JSON.stringify(user)));
    }

    login(username: string, password: string) : void {
      this.http.post<any>('api/user/login', {username: username, password: password}).pipe(
        tap(loginResponse => {
          if (loginResponse && loginResponse.token) {
            localStorage.setItem('currentUserToken', JSON.stringify(loginResponse));
          }
        }),
        flatMap(() => this.http.get<any>('api/user/current'))).pipe(first())
        .subscribe((user : UserDetail) => {
          this.currentUserEmitter.next(user);
        });
    }

    logout() {
      localStorage.removeItem('currentUserToken');
      localStorage.removeItem('currentUserDetail');
      this.currentUserEmitter.next(null); //TODO: correct logout: null user? Observable.complete? Sentinel pattern?
    }

    loggedIn() : boolean {
       return !this.currentUserEmitter.isEmpty;
    }

    currentUser() : Observable<UserDetail> { //TODO: after the update the authorised user is out of date;
      return this.currentUserEmitter;
    }

    test(){
      this.currentUserEmitter.subscribe(user => console.log(JSON.stringify(user)));
    }


}
