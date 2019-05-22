import { Component, OnInit } from '@angular/core';
import { GuiService, AuthenticationService } from './Services/index';

@Component({
    selector: 'app',
    templateUrl: 'app.component.html',
    styleUrls: ['app.component.scss']
})

export class AppComponent implements OnInit {

opened: boolean;

  constructor(private guiService: GuiService, private authService: AuthenticationService) { }

  ngOnInit() {
  	this.guiService.sideNavObservable.subscribe(() => {
      this.opened = !this.opened;
    })
  }

  get loggedIn(){
    return this.authService.loggedIn();
  }
}