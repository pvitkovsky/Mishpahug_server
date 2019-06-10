import {Component, OnInit} from '@angular/core';
import {ChoicesConnection, EventDetail} from '../../Models';
import {ChoicesService} from '../../Services/choices.service';
import {ActivatedRoute} from '@angular/router';
import {EventService} from '../../Services';

@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.scss']
})
export class EventDetailsComponent implements OnInit {

  choices : Map<string, string[]>;
  eventDetail : EventDetail; //TODO: input via router or as the child component
  eventId : number;

  constructor(private eventService: EventService, private choicesService: ChoicesService, private _route: ActivatedRoute) {
    this.choices = new Map<string, string[]>();
    this.eventDetail = new EventDetail();
  }

  ngOnInit() {
    this._route.params.subscribe(params => {
      this.eventService.getEvent(params['id']).subscribe(
        eventDetail => this.eventDetail = eventDetail
      )
    });

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
    console.log(this.eventDetail);
  }

  //TODO: update, etc

}
