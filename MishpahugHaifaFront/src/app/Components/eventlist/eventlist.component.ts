import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { filter } from 'rxjs/operators'
import { EventService } from '../../Services/index';
import { UserService } from '../../Services/index';

@Component({
  selector: 'app-eventlist',
  templateUrl: './eventlist.component.html',
  styleUrls: ['./eventlist.component.scss']
})
export class EventListComponent implements OnInit {


  @Input() array;
  @Output() output = new EventEmitter();

  events = [];
  keys = [];
  loggedInUserId: number;
  loggedInUserName : string;
  eventsExist: boolean = false;

  constructor(private router: Router, private route: ActivatedRoute, private eventService: EventService, private userService: UserService) { }
  
  ngOnInit() {
    this.route.url
    .pipe(filter(result => Object.keys(result).length  > 0))
    .subscribe(urlSegment => {
      var lastUrlPart = urlSegment[Math.max(0, urlSegment.length-1)].path
      if(lastUrlPart === "eventlist"){
        console.log('fullEventList')
        this.eventService.getEvents()
        .pipe(filter(result => Object.keys(result).length  > 0)) // checking that result has keys;
        .subscribe((response) => {
          this.keys = Object.keys(response[0]); //TODO: DRY;
          for (let event in response) {
            this.events.push(response[event]);
          }
          this.eventsExist = true; 
        });
      } else {
        this.userService.current().subscribe(
          userDetail => { //TODO: change subscribe into storing UserDetail on login; 
            console.log('current user id ' + userDetail.id)
            this.loggedInUserId = userDetail.id;
            this.loggedInUserName = userDetail.userName;
            this.router.navigate(['user', this.loggedInUserId, 'events'])
            this.eventService.getEventsByOwner(this.loggedInUserName)
            .pipe(filter(result => Object.keys(result).length > 0)) // checking that result has keys;
            .subscribe(
              response => {
                this.keys = Object.keys(response[0]);
                for (let event in response) {
                  this.events.push(response[event]);
                }
                this.eventsExist = true; 
              }, 
              error => {}      
              );
          });
      }
    });
  }
}

