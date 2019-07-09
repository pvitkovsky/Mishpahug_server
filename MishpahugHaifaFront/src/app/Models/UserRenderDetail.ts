import {UserDetail} from './UserDetail';

export class UserRenderDetail {

  id: number;
  firstName: string;
  lastName: string;
  phoneNumber: string;
  email: string;
  userName: string;
  currentCanEdit: boolean;

  constructor(detail: UserDetail, canEdit: boolean ){
    this.id = detail.id;
    this.firstName = detail.firstName;
    this.lastName = detail.lastName;
    this.phoneNumber = detail.phoneNumber;
    this.email = detail.email;
    this.userName = detail.userName;
    this.currentCanEdit = canEdit;
  }
}
