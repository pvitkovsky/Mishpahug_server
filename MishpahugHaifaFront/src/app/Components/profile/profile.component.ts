import {Component, EventEmitter, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router, UrlSegment} from '@angular/router';
import {AuthenticationService, UserService} from '../../Services/index';
import { UserDetail } from '../../Models/index';
import {combineLatest, Observable} from 'rxjs';
import {tap} from 'rxjs/internal/operators/tap';
import {first} from 'rxjs/internal/operators/first';


@Component({
	selector: 'app-profile',
	templateUrl: './profile.component.html',
	styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit, OnDestroy {

	renderedUserDetail: UserDetail;
	canEdit : boolean = false;

	constructor(
		private router: Router,
		private route: ActivatedRoute,
		private userService: UserService,
  private authService: AuthenticationService
  ) { }


	ngOnInit() {
	  this.route.pathFromRoot[2].url.flatMap(val => { // TODO: refactor without magic number (selected user id);
      if(!val[0]){
        return this.userService.current().pipe(first());
      } else { //TODO: Doesn't work with new ReplaySubject, investigate
        let renderedUserId : number = parseInt(val[0].path, 10)
        return this.userService.getById(renderedUserId);
      }
    }).subscribe(detail => {
      this.renderedUserDetail = detail;
    }, error => console.log("Not logged in"), () => console.log("Complete"));

	  this.userService.current().pipe(first()).map(userDetail => userDetail.id).subscribe(
	    loggedInUserId => {
	      this.canEdit = loggedInUserId === this.renderedUserDetail.id
      }
    );
	}

	ngOnDestroy(): void {
		//TODO: complete me
	}

	save(){
		this.userService.update(this.renderedUserDetail).subscribe(
			data => {
				this.renderedUserDetail = data;
			},
			error => {

			});
	}

	cancel(){
		this.userService.getById(this.renderedUserDetail.id).subscribe(
			data => {
				this.renderedUserDetail = data;
			},
			error => {

			});
	}

	test(){
  	// console.log(JSON.stringify(this.userService.current()));
     this.authService.test();
  }




}
