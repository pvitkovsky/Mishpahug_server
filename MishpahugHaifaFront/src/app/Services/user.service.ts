import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { UserDetail } from '../Models/index';

@Injectable()
export class UserService {
    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<UserDetail[]>('/api/user/');
    }

    getById(id: number) {
        return this.http.get('/api/user/' + id);
    }

    create(userDetail: UserDetail) {
        return this.http.post('/api/user/register', userDetail);
    }

    update(userDetail: UserDetail) {
        
        return this.http.put('/api/user/' + userDetail.id, userDetail); //TODO: dto into HashMap <String, String> for update() on backend;
    }

    delete(id: number) {
        return this.http.delete('/api/user/' + id);
    }
}
