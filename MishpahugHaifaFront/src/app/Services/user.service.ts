import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {EventDetail, UserDetail} from '../Models/index';
import { Observable } from 'rxjs';
import { AuthenticationService} from "./authentication.service"

@Injectable()
export class UserService {
    constructor(private http: HttpClient, private authService: AuthenticationService) { }

    //TODO: refactor with UserConnection to allow maintainable filtering;
    getAll() {
        return this.http.get<UserDetail[]>('/api/user/');//TODO: error handling
    }

    getById(id: number) : Observable<UserDetail> {
        //console.log('incoming to servie user id' + id);
        return this.http.get<UserDetail>('/api/user/' + id);
    }

    create(userDetail: UserDetail) {
        return this.http.post('/api/user/register', userDetail);
    }

    update(userDetail: UserDetail) : Observable<UserDetail>{
      // console.log('userDetail ' + JSON.stringify(userDetail));
      var res : Observable<UserDetail> = this.http.put<UserDetail>('/api/user/' + userDetail.id, userDetail);
      // console.log('  returned' +  JSON.stringify(res));
      return res;
    }

    delete(id: number) {
        return this.http.delete('/api/user/' + id);
    }

    current() : Observable<UserDetail> { //TODO: make UserDetail type work; make service memorise on auth;
        //console.log("call to UserService.current()" + this.authService.currentUser());
        return this.authService.currentUser();
    }

    loggedIn() :  boolean {
        return this.authService.loggedIn();
    }
}
