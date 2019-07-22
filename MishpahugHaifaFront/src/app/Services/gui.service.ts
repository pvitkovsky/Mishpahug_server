import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Subject';

@Injectable({
  providedIn: 'root'
})
export class GuiService {
  
  private actionSource = new Subject<boolean>(); // true == toggle; false == close;
  
  sideNavObservable = this.actionSource.asObservable(); //TODO : Subject+Observable into Subject;

  constructor() { }

  toggleSideNav() {
    this.actionSource.next(true)
  }

  closeSideNav() {
    this.actionSource.next(false)
  }
}


