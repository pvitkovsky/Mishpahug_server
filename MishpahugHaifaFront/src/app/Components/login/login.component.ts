import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {AuthenticationService} from '../../Services/index';

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
    private authenticationService: AuthenticationService) {
  }

  ngOnInit() {
    this.authenticationService.logout();
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  login() {
    this.loading = true;
    this.authenticationService.login(this.model.username, this.model.password).subscribe(
      (loggedIn: boolean) => {
        if (loggedIn) { //TODO: from if-else to FP style;
          this.router.navigate([this.returnUrl]);
        } else {
          this.loading = false;
        }
      }
    );

  }

  test() {
    this.authenticationService.test();
  }
}
