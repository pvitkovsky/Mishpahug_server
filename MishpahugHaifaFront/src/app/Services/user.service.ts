import {Injectable, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserDetail, UserRenderDetail} from '../Models/index';
import { AuthenticationService} from "./authentication.service"
import { of } from 'rxjs/observable/of';
import { filter } from 'rxjs/operators';
import { flatMap } from 'rxjs/operators';
import {Observable} from 'rxjs';

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

    updateCurrentUser(userRender: UserRenderDetail) : void {
      of(userRender)
        .pipe(
          filter( detail => detail.id === this.currentUserDetail.id),
          flatMap(userRender => this.http.put<UserDetail>('/api/user/' + userRender.id, new UserDetail(userRender))),
        )
        .subscribe(userDetail => this.authService.updateCurrentUser(userDetail));
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
