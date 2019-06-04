import { UserDetail } from './UserDetail';

export enum EventStatus {
	ALL = 0,
    INPROGRESS = 1,
    PENDING = 2,
    DONE = 3
}

export enum EventRelation {
	ALL = '',
    OWNER = 'byowner/',
    GUEST = 'byguest/',
}
/*
* State for requests to the EventController
*
*/
export class EventFilter {

    private userDetail: UserDetail;

    constructor(public eventStatus: EventStatus, public eventRelation: EventRelation, userDetail ? : UserDetail){
        this.eventStatus = eventStatus;
        this.eventRelation = eventRelation;
        if(userDetail){
            this.userDetail = userDetail;
        }
        //console.log("Arrived userDetail in constructor of Filter " + this.userDetail)
    }

    

    appendUserDetail() : string {
        switch (this.eventRelation) {
            case EventRelation.ALL:
            return '';
            default:{
                return this.userDetail.userName;
                // return (this.userDetail) ? this.userDetail.userName : '' ;    
            }
            
        }

    }
}




