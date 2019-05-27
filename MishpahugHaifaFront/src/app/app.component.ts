import { Component, OnInit } from '@angular/core';
import { GuiService, AuthenticationService, UserService } from './Services/index';
import { UserDetail } from './Models/index';

@Component({
    selector: 'app',
    templateUrl: 'app.component.html',
    styleUrls: ['app.component.scss']
})

export class AppComponent implements OnInit {

  userDetail: UserDetail;
  opened: boolean;

  constructor(private guiService: GuiService, private authService : AuthenticationService, private userService: UserService) { }

  ngOnInit() {
  	this.guiService.sideNavObservable.subscribe(() => {
      this.opened = !this.opened;
    });
    //this.userDetail = this.userService.current();
    this.authService.currentUser().subscribe( //TODO: into UserService please;
                data => {
                    this.userDetail = data;
                },
                error => {
                });
  }

  get loggedIn(){
    return this.authService.loggedIn();
  }
}