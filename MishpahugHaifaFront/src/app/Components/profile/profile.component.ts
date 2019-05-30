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
	renderedUserDetail: UserDetail; // TODO: rename to UserDetail to UserDTO?
	canEdit : boolean = false; // TODO: should always reflect the state of the component. Function?

	constructor(
		private router: Router,
		private route: ActivatedRoute,
		private userService: UserService) { }


	ngOnInit() { //TODO: does this run after relogin? i.e. canEdit relies on this...
		this.userService.current().subscribe(
			userDetail => { //TODO: change subscribe into storing UserDetail on login; 
				console.log('current user id ' + userDetail.id)
				this.loggedInUserId = userDetail.id;
				this.route.pathFromRoot[2].url.subscribe( // TODO: refactor without magic number;
					val => { //TODO: can we do this without subscribe? 
						if(!val[0]) {
							console.log('rendered user id ' + this.loggedInUserId)
							this.renderedUserId = this.loggedInUserId;
						} else {
							console.log('rendered user id ' + val[0].path)
							this.renderedUserId = parseInt(val[0].path, 10);
						}
						this.canEdit = (this.renderedUserId === this.loggedInUserId);
						this.userService.getById(this.renderedUserId).subscribe(
							userDetail => { //TODO: change subscribe into storing UserDetail on login; see above;
							    console.log('Received user detail' + userDetail) 
								this.renderedUserDetail = userDetail;
							},
							error => {});
					},
					error => {});
			},
			error =>{});
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
