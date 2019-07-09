import {Injectable, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserDetail, UserRenderDetail} from '../Models/index';
import { Observable } from 'rxjs';
import { AuthenticationService} from "./authentication.service"

@Injectable()
export class UserService {  //TODO: refactor with UserConnection to allow maintainable filtering;

    private currentUserDetail : UserDetail;

    constructor(private http: HttpClient, private authService: AuthenticationService) {
      this.authService.currentUser().subscribe(userDetail => this.currentUserDetail = userDetail);
    }

    getAll() {
        return this.http.get<UserDetail[]>('/api/user/');
    }

    private getById(id: number) : Observable<UserDetail> {
        return this.http.get<UserDetail>('/api/user/' + id);
    }

    getRenderById(id?: number) : Observable<UserRenderDetail> {
        if(!id || id === this.currentUserDetail.id){
          return this.authService.currentUser().map(userDetail => new UserRenderDetail(userDetail, true));
        } //   return Observable.of(new UserRenderDetail(this.currentUserDetail, true)); //TODO: make O.of() work;
        return this.getById(id).map(userDetail => new UserRenderDetail(userDetail, false));
    }

    create(userDetail: UserDetail) : Observable<UserRenderDetail> {
      return this.http.post<UserDetail>('/api/user/register', userDetail)
      .map(userDetail => new UserRenderDetail(userDetail, this.canEdit(userDetail)));
    }

    update(userRender: UserRenderDetail) : Observable<UserRenderDetail>{ //TODO: after the update the authorised user is out of date;
      return this.http.put<UserDetail>('/api/user/' + userRender.id, new UserDetail(userRender))
        .map(userDetail => new UserRenderDetail(userDetail, this.canEdit(userDetail)));
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

    private canEdit (detail : UserDetail) : boolean{
      return detail.id === this.currentUserDetail.id;
    }

}
