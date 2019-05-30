import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserDetail } from '../Models/index';
import { Observable, throwError } from 'rxjs';
import { AuthenticationService} from "./authentication.service"

@Injectable()
export class UserService {
    constructor(private http: HttpClient, private authService: AuthenticationService) { }

    getAll() {
        return this.http.get<UserDetail[]>('/api/user/');//TODO: error handling
    }

    getById(id: number) : Observable<UserDetail> {
        console.log('incoming to servie user id' + id);
        return this.http.get<UserDetail>('/api/user/' + id);
    }

    create(userDetail: UserDetail) {
        return this.http.post('/api/user/register', userDetail);
    }

    update(userDetail: UserDetail) : Observable<UserDetail>{
        return this.http.put<UserDetail>('/api/user/' + userDetail.id, userDetail);
    }

    delete(id: number) {
        return this.http.delete('/api/user/' + id);
    }

    current() : Observable<UserDetail> { //TODO: make UserDetail type work; make service memorise on auth;
        return this.authService.currentUser();
    }

    loggedIn() :  boolean {
        return this.authService.loggedIn();
    }
}
