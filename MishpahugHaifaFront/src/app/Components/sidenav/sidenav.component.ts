import { Component, OnInit } from '@angular/core';
import { GuiService } from '../../Services/index';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss']
})
export class SidenavComponent implements OnInit {

  events: string[] = [];
  opened: boolean;

  constructor(private guiService: GuiService) { }

  ngOnInit() {
  	this.guiService.sideNavObservable.subscribe(() => {
      this.opened = !this.opened;
    })
  }



}
