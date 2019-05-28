import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../../Services/index';
import { UserDetail } from '../../Models/index';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  
  userDetail: UserDetail; //TODO: rename to UserDTO?

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private userService: UserService) { }


  ngOnInit() {
  	//this.userDetail = this.userService.current();
    this.userService.current().subscribe( //TODO: into UserService please;
                data => {
                    this.userDetail = data;
                },
                error => {
                    
                });
  }

  save(){
  	this.userService.update(this.userDetail).subscribe(
                data => {
                    this.userDetail = data;
                },
                error => {
  
                });
  }

    cancel(){
  	this.userService.getById(this.userDetail.id).subscribe(
                data => {
                    this.userDetail = data;
                },
                error => {
  
                });
 	 }


}
