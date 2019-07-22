import {EventDetail} from './EventDetail';

export class EventRenderDetail {

  id: number;
  date: string;
  time: string;
  nameOfEvent: string;
  ownerId: number;
  guestIds: number[];
  currentCanEdit : boolean;
  currentIsSubscribed : boolean;
  constructor(detail: EventDetail, canEdit: boolean, currentIsSubscribed : boolean){
    this.id = detail.id;
    this.ownerId = detail.ownerId;
    this.date = detail.date;
    this.time = detail.time;
    this.nameOfEvent = detail.nameOfEvent;
    this.currentCanEdit = canEdit;
    this.currentIsSubscribed = currentIsSubscribed;
  }
}
