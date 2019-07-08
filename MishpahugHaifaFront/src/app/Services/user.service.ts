import {Injectable, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserDetail} from '../Models/index';
import { Observable } from 'rxjs';
import { AuthenticationService} from "./authentication.service"


@Injectable()
export class UserService {  //TODO: refactor with UserConnection to allow maintainable filtering;

    constructor(private http: HttpClient, private authService: AuthenticationService) {
    }

    getAll() {
        return this.http.get<UserDetail[]>('/api/user/');//TODO: error handling
    }

    getById(id: number) : Observable<UserDetail> {
        return this.http.get<UserDetail>('/api/user/' + id);
    }

    create(userDetail: UserDetail) {  //TODO: password encryption on this side please;
        return this.http.post('/api/user/register', userDetail);
    }

    update(userDetail: UserDetail) : Observable<UserDetail>{
      var res : Observable<UserDetail> = this.http.put<UserDetail>('/api/user/' + userDetail.id, userDetail);
      return res;
    }

    delete(id: number) {
        return this.http.delete('/api/user/' + id);
    }

    current() : Observable<UserDetail> {
        return  this.authService.currentUser();
    }

    loggedIn() : boolean {
        return this.authService.loggedIn();
    }

}
