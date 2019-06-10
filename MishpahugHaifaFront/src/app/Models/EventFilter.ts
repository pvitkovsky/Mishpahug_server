import { UserDetail } from './UserDetail';
import { EventConnection } from './Connections/EventConnection';

export enum EventStatus {
	ALL = 0,
  INPROGRESS = 1,
  PENDING = 2,
  DONE = 3
}

/*
* State for requests to the EventController
*
*/
export class EventFilter {

    private userDetail: UserDetail;

    constructor(public eventStatus: EventStatus, public eventConnection: EventConnection, userDetail ? : UserDetail){
        this.eventStatus = eventStatus;
        this.eventConnection = eventConnection;
        if(userDetail){
            this.userDetail = userDetail;
        }
        //console.log("Arrived userDetail in constructor of Filter " + this.userDetail)
    }

    appendUserDetail() : string {
        switch (this.eventConnection) {
            case EventConnection.ALL:
            return '';
            default:{
                return this.userDetail.userName;
                // return (this.userDetail) ? this.userDetail.userName : '' ;    
            }
            
        }

    }
}




