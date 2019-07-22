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
    this.fetchEvent();

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

  private fetchEvent() {
    this._route.params.subscribe(params => {
      this.eventService.getRenderEvent(params['id']).subscribe(
        eventDetail => {
          this.renderedEventDetail = eventDetail;
        }
      );
    });
  }

  // private create(updatedEvent: EventRenderDetail){

  // }

  private save(updatedEvent: EventRenderDetail){
    console.log("Arrived updated event " + JSON.stringify(updatedEvent));
    if(updatedEvent.id !== undefined){ //TODO: from if-else to FP style;
      console.log("updating event")
      this.eventService.updateEvent(updatedEvent).subscribe(
        data => {
          this.renderedEventDetail = data;
        });
    } else {
      console.log("saving event")
      this.eventService.createEvent(updatedEvent).subscribe(
        data => {
          this.renderedEventDetail = data;
        });
    }

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

  private subscribe(){ //Somewhere have to update?
    this.subscriptionService.sub(this.renderedEventDetail.id).subscribe(res => this.fetchEvent()) // can I do map from this into next?
  }

  private unsubscribe(){
    this.subscriptionService.unsub(this.renderedEventDetail.id).subscribe(res => this.fetchEvent());
  }

  /**
   * Series of event-driven methods
   */
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

