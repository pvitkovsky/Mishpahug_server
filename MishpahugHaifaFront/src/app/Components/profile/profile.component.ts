import {Component, OnDestroy, OnInit} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../../Services/index';
import { UserDetail } from '../../Models/index';


@Component({
	selector: 'app-profile',
	templateUrl: './profile.component.html',
	styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit, OnDestroy { //  TODO: onDestroy bc we have subscriptions here;
	loggedInUserId: number;
  renderedUserId: number;
	renderedUserDetail: UserDetail; // TODO: rename to UserDTO?

	constructor(
		private router: Router,
		private route: ActivatedRoute,
		private userService: UserService) { }


	ngOnInit() { //TODO: make this execute sequentally, e.g. do not do part 2 and 3 until part 1 is done;
    //part 1
	  this.userService.current().subscribe(
      userDetail => {
        console.log('current user id' + userDetail.id)
        this.loggedInUserId = userDetail.id;
      },
      error =>{});

	  //part 2
		this.route.pathFromRoot[2].url.subscribe( // TODO: refactor without magic number;
			val => {
        if(!val[0]) {
          console.log('rendered user id' + this.loggedInUserId)
          this.renderedUserId = this.loggedInUserId;
        } else {
          console.log('rendered user id' + val[0].path)
          this.renderedUserId = parseInt(val[0].path, 10);
        }
			},
			error => {});

		//part 3
		this.userService.getById(this.renderedUserId).subscribe( // TODO: limit the app to selecting only the active ID;
			userDetail => {
				this.renderedUserDetail = userDetail;
			},
			error => {});
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
