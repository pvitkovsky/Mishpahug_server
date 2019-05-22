import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { GuiService, AuthenticationService } from '../../Services/index';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  //TODO: consider merging this with app.component
   
  constructor(private guiService: GuiService, private authService: AuthenticationService ) { 
  }

  ngOnInit() {
  }

  sideMenu() {
     this.guiService.toggleSideNav();
  }

  get loggedIn(){
    return this.authService.loggedIn();
  }

}
