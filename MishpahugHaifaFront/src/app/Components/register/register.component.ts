import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {User, UserDetail, UserRenderDetail} from '../../Models/index';

import { AlertService, UserService } from '../../Services/index';

@Component({
    templateUrl: 'register.component.html',
    styleUrls: ['register.component.scss']
})

export class RegisterComponent {
    userDetail: UserDetail = new UserDetail();
    loading = false;

    constructor(
        private router: Router,
        private userService: UserService) { }

    register() {
        this.loading = true;
        this.userService.create(this.userDetail)
            .subscribe(
              (regSuccess : boolean) => {
                    if(regSuccess){ //TODO: from if-else to FP style;
                      this.router.navigate(['/login']);
                    } else {
                      console.log("User "+ JSON.stringify(this.userDetail)+" already exists");
                      this.loading = false;
                    }

                },
                error => {
                    this.loading = false;
                });
    }
}
