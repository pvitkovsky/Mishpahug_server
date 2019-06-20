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

    constructor(private eventStatus: EventStatus, private eventConnection: EventConnection, userDetail ? : UserDetail){
        this.eventStatus = eventStatus;
        this.eventConnection = eventConnection;
        if(userDetail){
            this.userDetail = userDetail;
        }
        //console.log("Arrived userDetail in constructor of Filter " + this.userDetail)
    }

    combineConnectionString() : string {
        switch (this.eventConnection) {
            case EventConnection.ALL: {
              return EventConnection.ALL;
              break;
            }
            case EventConnection.OWNER: {
              return EventConnection.OWNER + this.userDetail.userName;
              break;
            }
            default: {
              return '';
              break;
            }
            
        }

    }
}




