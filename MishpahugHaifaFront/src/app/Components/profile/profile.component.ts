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
  
  user: any; //TODO: typed user please

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private authService: AuthenticationService) { }


  ngOnInit() {
  	this.authService.currentUser().subscribe(
                data => {
                    this.user = data;
                },
                error => {
  
                });
  }

  test(){
  	console.log(this.user); 
  }

}
