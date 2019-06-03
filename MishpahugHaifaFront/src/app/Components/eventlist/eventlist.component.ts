import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { filter } from 'rxjs/operators'

export enum RenderEvents {
    ALL = 'ALL',
    OWNER = 'OWNER',
    GUEST = 'GUEST'
}

@Component({
  selector: 'app-eventlist',
  templateUrl: './eventlist.component.html',
  styleUrls: ['./eventlist.component.scss']
})
export class EventListComponent implements OnInit {

  renderEvents : RenderEvents; 

  constructor(private router: Router, private route: ActivatedRoute) { }
  
  ngOnInit() {
//     this.route.url
//     .pipe(filter(result => Object.keys(result).length  > 0))
//     .subscribe(urlSegment => {
//       var lastUrlPart = urlSegment[Math.max(0, urlSegment.length-1)].path
//       if(lastUrlPart == "all-events"){
//         this.renderEvents = RenderEvents.ALL;
//       } 
//       if(lastUrlPart == "my-events"){
//         this.renderEvents = RenderEvents.OWNER;
// //        this.router.navigate(['user', this.loggedInUserId, 'events'])
//       }
//       if(lastUrlPart == "my-subscriptions"){
//         this.renderEvents = RenderEvents.GUEST;
//       }
//     });
    this.renderEvents = RenderEvents.ALL; //TODO: routing
    //console.log(this.renderEvents);
  }
}

