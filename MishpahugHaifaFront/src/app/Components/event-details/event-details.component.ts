import {Component, OnInit} from '@angular/core';
import {ChoicesConnection, EventDetail} from '../../Models';
import {ChoicesService} from '../../Services/choices.service';
import {ActivatedRoute} from '@angular/router';
import {EventService, UserService} from '../../Services';

@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.scss']
})
export class EventDetailsComponent implements OnInit {

  choices : Map<string, string[]>;
  renderedEventDetail : EventDetail; //TODO: input via router or as the child component
  eventId : number;
  canEdit : boolean = false;

  constructor(private eventService: EventService, private choicesService: ChoicesService, private _route: ActivatedRoute, private userService: UserService) {
    this.choices = new Map<string, string[]>();
    this.renderedEventDetail = new EventDetail();

  }

  ngOnInit() {
    this._route.params.subscribe(params => {
      this.eventService.getEvent(params['id']).subscribe(
        eventDetail => this.renderedEventDetail = eventDetail
      )
    });

    this.userService.current().subscribe(
      userDetail => {
        console.log(' arrived userDetail ' + userDetail.id + ' and the event owner id is ' + this.renderedEventDetail.ownerId);
        this.canEdit = (userDetail.id === this.renderedEventDetail.ownerId) ;} //TODO: mergeMap or something with renderedEventDetail to ensure sync;
    );


    for (let choiceName in ChoicesConnection){
      let typedChoice : keyof typeof ChoicesConnection = choiceName as keyof typeof ChoicesConnection; // getting enum out of string;
      let choiceVariants : string[];
      this.choicesService.getOptions(ChoicesConnection[typedChoice]).subscribe(res => {
        choiceVariants = res
        this.choices.set(choiceName, choiceVariants);
      });
    }
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

  //TODO: update, etc

}
