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
        return this.http.get<UserDetail>('/api/user/' + id);
    }

    create(userDetail: UserDetail) {
        return this.http.post('/api/user/register', userDetail);
    }

    update(userDetail: UserDetail) : Observable<UserDetail>{
        return this.http.put<UserDetail>('/api/user/' + userDetail.id, userDetail)
          .pipe(
            //TODO: error handling
          );
    }

    delete(id: number) {
        return this.http.delete('/api/user/' + id);
    }

    current() : any { //TODO: make UserDetail type work; make this work
        this.authService.currentUser().subscribe(
                data => {
                    return data;
                },
                error => {
                    return new UserDetail();
                });
    }

    //TODO: loggedIn from AuthSerice
}
