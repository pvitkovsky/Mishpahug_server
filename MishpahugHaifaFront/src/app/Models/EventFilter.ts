import { UserDetail } from './UserDetail';
import {EventConnection, EVENTROOT} from './Connections/EventConnection';
import {USERROOT} from './Connections/UserConnection';

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

    getConnectionString() : string {
        switch (this.eventConnection) {
            case EventConnection.ALL: {
              return EVENTROOT + EventConnection.ALL;
              break;
            }
            case EventConnection.OWNER: {
              return USERROOT + this.userDetail.id + EventConnection.OWNER ;
              break;
            }
            case EventConnection.GUEST: {
              return USERROOT + this.userDetail.id + EventConnection.GUEST ;
            break;
            }
            default: {
              return '';
              break;
            }
            
        }

    }
}




