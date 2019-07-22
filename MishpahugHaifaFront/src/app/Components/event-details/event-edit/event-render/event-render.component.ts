import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {EventRenderDetail} from '../../../../Models';

@Component({
  selector: 'app-event-render',
  templateUrl: './event-render.component.html',
  styleUrls: ['./event-render.component.scss']
})
export class EventRenderComponent implements OnInit {

  @Input() renderedEventDetail : EventRenderDetail;
  @Input() choices : Map<string, string[]>;
  @Output() eventDetailUpdates = new EventEmitter<EventRenderDetail>();
  @Output() subscribedUpdates : EventEmitter<boolean> = new EventEmitter<boolean>(); //TODO: doesn't bind with single boolean emitter;
  @Output() unSubscribedUpdates : EventEmitter<boolean> = new EventEmitter<boolean>();

  constructor() {
  }

  save(){
    this.eventDetailUpdates.emit(this.renderedEventDetail);// save
  }

  cancel(){
    return null;
  }

  create(){
    this.renderedEventDetail = new EventRenderDetail(null, true, false);
  }

  subscribe(){
    this.subscribedUpdates.emit(true);
  }

  unsubscribe(){
    this.unSubscribedUpdates.emit(true);
  }


  ngOnInit() {
  }

}
