import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserDetail } from '../../Models/index';

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
        private userService: UserService,
        private alertService: AlertService) { }

    register() {
        this.loading = true;
        this.userService.create(this.userDetail)
            .subscribe(
                data => {
                    this.alertService.success('Registration successful', true);
                    this.router.navigate(['/login']);
                },
                error => {
                    this.alertService.error(error);
                    this.loading = false;
                });
    }
}
