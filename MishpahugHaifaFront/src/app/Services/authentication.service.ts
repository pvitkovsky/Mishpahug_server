import {EventEmitter, Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import {UserDetail} from '../Models';
import {tap} from 'rxjs/internal/operators/tap';
import {first, flatMap, map, takeLast} from 'rxjs/internal/operators';
import {BehaviorSubject, ReplaySubject} from 'rxjs';
import {getMatIconFailedToSanitizeLiteralError} from '@angular/material';
import {of} from 'rxjs/internal/observable/of';

@Injectable()
export class AuthenticationService {

    private currentUserDetail = JSON.parse(localStorage.getItem('currentUserDetail')) ;
    private currentUserEmitter: BehaviorSubject<UserDetail> =
        new BehaviorSubject(this.currentUserDetail);
    private loginReadyEmitter: BehaviorSubject<boolean> =  new BehaviorSubject(false);

    constructor(private http: HttpClient) {
      this.currentUserEmitter.subscribe(user => localStorage.setItem('currentUserDetail', JSON.stringify(user)));
    }

    login(username: string, password: string) : Observable<boolean> {
      this.http.post<any>('api/user/login', {username: username, password: password}).pipe(
        tap(loginResponse => {
          if (loginResponse && loginResponse.token) { //TODO: from if-else to FP style;
            localStorage.setItem('currentUserToken', JSON.stringify(loginResponse));
            this.loginReadyEmitter.next(true);
          } else {
            this.loginReadyEmitter.next(false);
          }
        }),
        flatMap(() => this.http.get<any>('api/user/current'))).pipe(first())
        .subscribe((user : UserDetail) => {
          this.currentUserEmitter.next(user);
        })
      return this.loginReadyEmitter;
    }

    logout() {
      localStorage.removeItem('currentUserToken');
      localStorage.removeItem('currentUserDetail');
    }

    loggedIn() : boolean { //TODO: refactor into better solution with currentUserEmitter;
       return localStorage.getItem('currentUserToken') !== null;
    }

    currentUser() : Observable<UserDetail> {
      return this.currentUserEmitter;
    }

    test(){
      this.currentUserEmitter.subscribe(user => console.log(JSON.stringify(user)));
    }

    updateCurrentUser(newCurrentUserDetail : UserDetail) : void {
      this.currentUserEmitter.next(newCurrentUserDetail);
    }


}
