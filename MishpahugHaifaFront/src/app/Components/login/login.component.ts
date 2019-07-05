import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { AlertService, AuthenticationService } from '../../Services/index';

@Component({
    templateUrl: 'login.component.html',
    styleUrls: ['login.component.scss']

})

export class LoginComponent implements OnInit {
    model: any = {};
    loading = false;
    returnUrl: string;

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private authenticationService: AuthenticationService,
        private alertService: AlertService) { }

    ngOnInit() {
        // reset login status
        this.authenticationService.logout();

        // get return url from route parameters or default to '/'
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    }

    login() {
        this.loading = true;
        this.authenticationService.login(this.model.username, this.model.password)
        this.authenticationService.loggedIn().subscribe(
          loggedIn => {
            if (loggedIn) {
              this.router.navigate([this.returnUrl]);
            } else {
              this.loading = false;
            }
          });
    }

    test(){
        console.log(JSON.stringify(this.authenticationService.currentUser()));
    }
}
