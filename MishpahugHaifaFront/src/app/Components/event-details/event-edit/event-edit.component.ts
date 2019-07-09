import {Component, OnInit } from '@angular/core';
import {EventRenderDetail} from '../../../Models';
import {ChoicesService} from '../../../Services/choices.service';
import {ActivatedRoute} from '@angular/router';
import {EventService, SubscriptionService } from '../../../Services';

@Component({
  selector: 'app-event-edit',
  templateUrl: './event-edit.component.html',
  styleUrls: ['./event-edit.component.scss'],
})
export class EventEditComponent implements OnInit {

  renderedEventDetail : EventRenderDetail; //TODO: input via router or as the child component
  choices : Map<string, string[]>;

  constructor(private eventService: EventService,
              private choicesService: ChoicesService,
              private _route: ActivatedRoute,
              private subscriptionService : SubscriptionService) {
    this.choices = new Map<string, string[]>();
  }

  ngOnInit() {
    this._route.params.subscribe(params => {
      this.eventService.getRenderEvent(params['id']).subscribe(
        eventDetail => {
          this.renderedEventDetail = eventDetail
        }
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

  private create(){
    this.eventService.createEvent(this.renderedEventDetail).subscribe(
      data => {
        this.renderedEventDetail = data;
      });
  }

  private save(updatedEvent: EventRenderDetail){
    this.eventService.updateEvent(this.renderedEventDetail).subscribe(
      data => {
        this.renderedEventDetail = data;
      });
  }

  private delete(){
    this.eventService.deleteEvent(this.renderedEventDetail).subscribe(
      data => {
        this.renderedEventDetail = data;
      });
  }

  private cancel(){ //TODO: into child component;
    this._route.params.subscribe(params => {
      this.eventService.getRenderEvent(params['id']).subscribe(
        eventDetail => this.renderedEventDetail = eventDetail
      )
    });
  }

  private subscribe(){
    this.subscriptionService.subscribe(this.renderedEventDetail.id);
  }

  private unsubscribe(){
    this.subscriptionService.unsubscribe(this.renderedEventDetail.id);
  }


  updateEvent($event : EventRenderDetail){
    this.save($event);
  }

  updateSubscription(){
    this.subscribe();
  }

  updateUnSubscription(){
    this.unsubscribe();
  }

  showEventDetail(){
    console.log(this.renderedEventDetail);
  }

  test(){
    console.log((this.renderedEventDetail));
  }

}

