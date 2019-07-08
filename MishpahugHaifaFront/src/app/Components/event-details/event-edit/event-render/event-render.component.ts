import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {EventDetail} from '../../../../Models';

@Component({
  selector: 'app-event-render',
  templateUrl: './event-render.component.html',
  styleUrls: ['./event-render.component.scss']
})
export class EventRenderComponent implements OnInit, OnChanges {

  @Input() renderedEventDetail : EventDetail;
  @Input() choices : Map<string, string[]>;
  @Input() canEdit: boolean;
  @Input() isSubscribed : boolean;

  @Output()
  eventDetailUpdates = new EventEmitter<EventDetail>();


  constructor() {
  }

  save(){
    this.eventDetailUpdates.emit(this.renderedEventDetail);
  }


  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges): void {
    //  if (changes['renderedEventDetail']) { //TODO: something passes undefined. find out what
    //   console.log(JSON.stringify(changes['renderedEventDetail']));
    // }
    // if (changes['canEdit']) { //TODO: something passes undefined. find out what
    //   console.log(JSON.stringify(changes['canEdit']));
    // }
  }

}
