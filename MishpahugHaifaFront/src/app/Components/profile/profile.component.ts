import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from '../../Services/index';
import { User } from '../../Models/user';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  
  user: User;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private authService: AuthenticationService) { }


  ngOnInit() {
  	this.user = JSON.parse(this.authService.currentUser());//TODO: returns token; make it return the user object
  }

  test(){
  	console.log(this.user); 
  }

}
