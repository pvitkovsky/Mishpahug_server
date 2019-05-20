import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { GuiService } from '../../Services/index';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor(private guiService: GuiService) { 
  }

  ngOnInit() {
  }

  sideMenu() {
     this.guiService.toggleSideNav();
  }

}
