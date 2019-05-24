import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService, UserService } from '../../Services/index';
import { UserDetail } from '../../Models/index';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  
  userDetail: UserDetail; //TODO: typed user please

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private authService: AuthenticationService,
    private userService: UserService) { }


  ngOnInit() {
  	this.authService.currentUser().subscribe(
                data => {
                    this.userDetail = data;
                },
                error => {
  
                });
  }

  test(){
  	console.log(this.userDetail); 
  }

  save(){
  	this.userDetail.lastName = this.userDetail.lastName + " updated ";
  	this.userService.update(this.userDetail);
  }

}
