import {Component, EventEmitter, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router, UrlSegment} from '@angular/router';
import { UserService } from '../../Services/index';
import { UserDetail } from '../../Models/index';
import { combineLatest } from "rxjs";

@Component({
	selector: 'app-profile',
	templateUrl: './profile.component.html',
	styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit, OnDestroy { //  TODO: onDestroy bc we have subscriptions here;

	renderedUserDetail: UserDetail;
	canEdit : boolean;

  loggedInUserEmitter: EventEmitter<number> = new EventEmitter();
  renderedUserEmitter: EventEmitter<number> = new EventEmitter();

	constructor(
		private router: Router,
		private route: ActivatedRoute,
		private userService: UserService) { }


	ngOnInit() {

    this.route.pathFromRoot[2].url.flatMap(val => { // TODO: refactor without magic number (selected user id);
      if(!val[0]){
        return this.loggedInUserEmitter.flatMap(loggedInUserId => this.userService.getById(loggedInUserId));
      } else {
        return this.userService.getById(parseInt(val[0].path, 10));
      }
    }).subscribe(detail => {
      this.renderedUserDetail = detail
      this.renderedUserEmitter.emit(detail.id);
    });

    this.userService.current().subscribe(
      userDetail => {
      this.loggedInUserEmitter.emit(userDetail.id);
    });

    combineLatest(this.loggedInUserEmitter, this.renderedUserEmitter).subscribe(ids => { // TODO: refactor without depreciation;
      this.canEdit = (ids[0] === ids[1]);
    });

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



}
