import {EventRenderDetail} from './EventRenderDetail';

export class EventDetail {

     id: number;
     date: string;
     time: string;
     nameOfEvent: string;
     addressCountry : string;
     addressCity: string;
     addressStreet : string;
     addressBuild : number ;
     addressApartment : number;
     holiday : string;
     holidayDescription : string;
     religion: string;
     kitchen : string;
     ownerId: number;
     guestIds: number[];

  constructor(render: EventRenderDetail){
    this.id = render.id;
    this.ownerId = render.ownerId;
    this.date = render.date;
    this.time = render.time;
    this.nameOfEvent = render.nameOfEvent;
  }

}
