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

  userDetail: UserDetail;
  opened: boolean;

  constructor(private guiService: GuiService, private userService: UserService, private subscriptionService : SubscriptionService) { }

  ngOnInit() {
  	this.guiService.sideNavObservable.subscribe(() => {
      this.opened = !this.opened;
    });
    this.userService.current().subscribe(
                data => {
                    this.userDetail = data;
                },
                error => {
                });
  }


  get loggedIn(){
    return this.userService.loggedIn();
  }

  subscribe(){
    this.subscriptionService.subscribe(1, 2);
  }

  unsubscribe(){
    this.subscriptionService.unsubscribe(1, 2);

  }

}
