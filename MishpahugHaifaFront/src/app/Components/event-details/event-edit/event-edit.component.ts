import {Component, EventEmitter, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {ChoicesConnection, EventDetail} from '../../../Models';
import {ChoicesService} from '../../../Services/choices.service';
import {ActivatedRoute} from '@angular/router';
import {EventService, SubscriptionService, UserService} from '../../../Services';
import {combineLatest} from 'rxjs/internal/operators/combineLatest';
import {mergeMap} from 'rxjs-compat/operator/mergeMap';
import {zip} from 'rxjs/internal/observable/zip';
import {withLatestFrom} from 'rxjs/internal/operators/withLatestFrom';
import {first} from 'rxjs/operators';
import {ReplaySubject, Subject} from 'rxjs';

@Component({
  selector: 'app-event-edit',
  templateUrl: './event-edit.component.html',
  styleUrls: ['./event-edit.component.scss'],
})
export class EventEditComponent implements OnInit {

  //TODO: maybe refactor this component into logic and two displays (edit and view?);

  loggedInUserId : number;
  renderedEventDetail : EventDetail; //TODO: input via router or as the child component
  choices : Map<string, string[]>;

  canEdit : boolean = false;
  canSubscribe : boolean = false;
  canUnSubscribe : boolean = false;

  loggedInUserEmitter: ReplaySubject<number> = new ReplaySubject(1);
  renderedEventEmitter: ReplaySubject<EventDetail> = new ReplaySubject(1);

  constructor(private eventService: EventService,
              private choicesService: ChoicesService,
              private _route: ActivatedRoute,
              private userService: UserService,
              private subscriptionService : SubscriptionService) {
    this.choices = new Map<string, string[]>();
    this.renderedEventDetail = new EventDetail();
  }

  ngOnInit() {
    this._route.params.subscribe(params => {
      this.eventService.getEvent(params['id']).subscribe(
        eventDetail => {
          this.renderedEventEmitter.next(eventDetail);
          this.renderedEventDetail = eventDetail
        }
      )
    });

    this.userService.current().subscribe(
      userDetail => {
        this.loggedInUserEmitter.next(userDetail.id);
        this.loggedInUserId = userDetail.id;
      }
    );

    this.renderedEventEmitter
      .pipe(combineLatest(this.loggedInUserEmitter))
      .subscribe((userIdAndEventDetail : [EventDetail, number] )=> {
      let currentUserId : number = userIdAndEventDetail[1];
      let eventOwnerId : number = userIdAndEventDetail[0].ownerId;
      let guests : number[] = userIdAndEventDetail[0].guestIds;
      let isOwner = currentUserId === eventOwnerId;
      console.log("is owner? " + isOwner);
      this.canEdit = isOwner;
      this.canSubscribe = (
        !isOwner &&
        !guests.includes(currentUserId)
      );
      this.canUnSubscribe = (
        !isOwner &&
        guests.includes(currentUserId)
      )
    });

    // this.renderedEventEmitter.subscribe(val => console.log(" event receieved"));
    // this.loggedInUserEmitter.subscribe(val => console.log(" user receieved"));

    //TODO: get choices back;
    // for (let choiceName in ChoicesConnection){
    //   let typedChoice : keyof typeof ChoicesConnection = choiceName as keyof typeof ChoicesConnection; // getting enum out of string;
    //   let choiceVariants : string[];
    //   this.choicesService.getOptions(ChoicesConnection[typedChoice]).subscribe(res => {
    //     choiceVariants = res
    //     this.choices.set(choiceName, choiceVariants);
    //   });
    // }
  }

  showEventDetail(){
    console.log(this.renderedEventDetail);
    console.log(this.loggedInUserId);
    console.log(this.canSubscribe);
    console.log(this.canUnSubscribe);
  }

  create(){
    let newEventDetail = this.renderedEventDetail;
    newEventDetail.ownerId = this.loggedInUserId;
    this.eventService.createEvent(newEventDetail).subscribe(
      data => {
        this.renderedEventDetail = data;
      });
  }

  save(updatedEvent: EventDetail ){
    this.eventService.updateEvent(updatedEvent).subscribe(
      data => {
        this.renderedEventDetail = data;
      });
  }

  delete(){
    this.eventService.deleteEvent(this.renderedEventDetail).subscribe(
      data => {
        this.renderedEventDetail = data;
      });
  }

  cancel(){ //TODO: into child component;
    this._route.params.subscribe(params => {
      this.eventService.getEvent(params['id']).subscribe(
        eventDetail => this.renderedEventDetail = eventDetail
      )
    });
  }

  subscribe(){
    this.subscriptionService.subscribe(this.renderedEventDetail.id, this.loggedInUserId);
  }

  unsubscribe(){
    this.subscriptionService.unsubscribe(this.renderedEventDetail.id, this.loggedInUserId);
  }


  updateEvent(updatedEventDetail){ //first function called from the child; make it save too;
    console.log("updateEvent called " + JSON.stringify(updatedEventDetail));
    this.save(updatedEventDetail);
  }

}

