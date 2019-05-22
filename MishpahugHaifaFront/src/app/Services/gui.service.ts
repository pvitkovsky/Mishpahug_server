import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Subject';

@Injectable({
  providedIn: 'root'
})
export class GuiService {
  
  private actionSource = new Subject<boolean>();
  
  sideNavObservable = this.actionSource.asObservable();

  constructor() { }

  toggleSideNav() {
    this.actionSource.next(true)
  }
}
