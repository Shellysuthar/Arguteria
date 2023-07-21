import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import User from 'src/app/model/User';
import { AuthService } from 'src/app/services/auth/auth.service';
import { SharedDataService } from 'src/app/services/sharedData/shared-data.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  userDetails!: User;
  constructor(
    private sharedDataService: SharedDataService,
    private authService: AuthService,
    private router: Router,
    private cdr: ChangeDetectorRef,
    private toastr: ToastrService
  ) {}
  ngOnInit(): void {
    this.sharedDataService.userDetailsObservable.subscribe((userDetails) => {
      this.userDetails = userDetails;
    });
    // console.log(this.userDetails);
  }
  isAuthenticated() {
    return this.authService.isAuthenticated();
  }

  logOut() {
    this.authService.logout();
    this.sharedDataService.setUserDetails('');
    // this.sharedDataService.setUserRole('');
    this.toastr.info('You are logged out', 'Log out successful');
    this.router.navigate(['/home']);
  }

  getRole() {
    return this.authService.getRole();
  }
}
