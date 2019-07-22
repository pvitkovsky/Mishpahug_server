import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      // console.log(JSON.stringify(request.body));
      // add authorization header with jwt token if available
        let currentUser = JSON.parse(localStorage.getItem('currentUserToken'));
        if (currentUser && currentUser.token) {
            // console.log("adding security token" + currentUser.token);
            request = request.clone({
                setHeaders: { 
                    Authorization: `${currentUser.token}`
                }
            });
        }
        //console.log(request);
        return next.handle(request);
    }
}
