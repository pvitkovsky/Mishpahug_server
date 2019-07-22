import {UserRenderDetail} from './UserRenderDetail';

export class UserDetail {

     id: number;
     firstName: string;
     lastName: string;
     phoneNumber: string;
     email: string;
     userName: string;
     encryptedPassword: string;
     confirmedPassword: string;

  constructor(render?: UserRenderDetail){
    if(render) {
      this.id = render.id;
      this.firstName = render.firstName;
      this.lastName = render.lastName;
      this.phoneNumber = render.phoneNumber;
      this.email = render.email;
      this.userName = render.userName;
    }
  }
}
