import { Component, OnInit } from '@angular/core';
import { GuiService, UserService } from './Services/index';
import { UserDetail } from './Models/index';
import {SubscriptionService} from './Services/subscription.service';


@Component({
    selector: 'app',
    templateUrl: 'app.component.html',
    styleUrls: ['app.component.scss']
})

export class AppComponent implements OnInit {

  userDetail: UserDetail = undefined;
  opened: boolean;

  constructor(private guiService: GuiService, private userService: UserService) { }

  ngOnInit() {
  	this.guiService.sideNavObservable.subscribe((toggle : boolean) => {
  	  if(toggle) {
        this.opened = !this.opened;
      } else {
  	    this.opened = false;
      }
    });
  	this.userService.current().subscribe((userDetail : UserDetail) => this.userDetail = userDetail);
  }


  get loggedIn() : boolean {
    return this.userDetail ? true : false;
  }

}
