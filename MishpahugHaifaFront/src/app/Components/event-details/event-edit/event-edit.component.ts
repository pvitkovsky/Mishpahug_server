import {Component, EventEmitter, OnInit} from '@angular/core';
import {ChoicesConnection, EventDetail} from '../../../Models';
import {ChoicesService} from '../../../Services/choices.service';
import {ActivatedRoute} from '@angular/router';
import {EventService, SubscriptionService, UserService} from '../../../Services';
import {combineLatest} from 'rxjs';
import {mergeMap} from 'rxjs-compat/operator/mergeMap';
import {zip} from 'rxjs/internal/observable/zip';

@Component({
  selector: 'app-event-edit',
  templateUrl: './event-edit.component.html',
  styleUrls: ['./event-edit.component.scss']
})
export class EventEditComponent implements OnInit {


  loggedInUserId : number;
  renderedEventDetail : EventDetail; //TODO: input via router or as the child component
  choices : Map<string, string[]>;

  canEdit : boolean = false;
  canSubscribe : boolean = false;
  canUnSubscribe : boolean = false;

  loggedInUserEmitter: EventEmitter<number> = new EventEmitter();
  renderedEventEmitter: EventEmitter<EventDetail> = new EventEmitter();

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
          console.log(' arrived eventDetail ' + JSON.stringify(eventDetail));
          this.renderedEventEmitter.emit(eventDetail);
          this.renderedEventDetail = eventDetail
        }
      )
    });

    this.userService.current().subscribe(
      userDetail => {
        console.log(' arrived userDetail ' + JSON.stringify(userDetail));
        this.loggedInUserEmitter.emit(userDetail.id);
        this.loggedInUserId = userDetail.id;
      }
    );

    combineLatest(this.loggedInUserEmitter, this.renderedEventEmitter).subscribe(userIdAndEventDetail  => { //TODO: get rid of ts-ignore
      console.log(' arrived merge of u an e  ' + JSON.stringify(userIdAndEventDetail));
      // @ts-ignore
      var isOwner = userIdAndEventDetail[0] === userIdAndEventDetail[1].id;
      this.canEdit = isOwner;
      // @ts-ignore
      this.canSubscribe = (
        !isOwner &&
        !userIdAndEventDetail[1].guestIds.includes(userIdAndEventDetail[0])
      );
      // @ts-ignore
      this.canUnSubscribe = (
        !isOwner &&
        userIdAndEventDetail[1].guestIds.includes(userIdAndEventDetail[0])
      )
    });

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
  }

  save(){
    this.eventService.updateEvent(this.renderedEventDetail).subscribe(
      data => {
        this.renderedEventDetail = data;
      });
  }

  cancel(){
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
  //TODO: update, etc

}
