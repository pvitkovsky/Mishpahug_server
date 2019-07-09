import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router } from '@angular/router';
import {UserService} from '../../Services/index';
import {UserRenderDetail} from '../../Models/index';
import {flatMap} from 'rxjs/operators';

@Component({
	selector: 'app-profile',
	templateUrl: './profile.component.html',
	styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit, OnDestroy {

	renderedUserDetail: UserRenderDetail;

	constructor(
		private router: Router,
		private route: ActivatedRoute,
		private userService: UserService,
  ) { }

	ngOnInit() {
	  this.route.pathFromRoot[2].url.pipe(
      flatMap(val => { // TODO: refactor without magic number (selected user id);
        console.log(JSON.stringify(val[0]));
        if(val[0]){
          return this.userService.getRenderById(parseInt(val[0].path, 10));
        } else {
          return this.userService.getRenderById();
        }
      })
    ).subscribe((detail : UserRenderDetail) => {
          this.renderedUserDetail = detail;
    },  error => console.log("Not logged in"), () => console.log("Complete"));
	}

	ngOnDestroy(): void {
		//TODO: complete me
	}

	save(){
		this.userService.update(this.renderedUserDetail).subscribe(
			data => {
				this.renderedUserDetail = data;
			});
	}

	cancel(){
		this.userService.getRenderById(this.renderedUserDetail.id).subscribe(
			data => {
				this.renderedUserDetail = data;
			});
	}

	test(){
	  console.log(JSON.stringify(this.renderedUserDetail))
  }
}
