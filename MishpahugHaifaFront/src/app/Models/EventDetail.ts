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
     guestsIds: number[];
     // Problem: hashmap string-string can't let the array of guestId's in;
     // because the backend wants map <string, string>, and guestIds are number ;
     //TODO: disable guestIds stringifying;
}
