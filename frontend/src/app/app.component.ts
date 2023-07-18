import { Component } from '@angular/core';
import { Router, RouterModule} from '@angular/router';
import { AuthService } from './services/auth/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  [x: string]: any;
  isCollapsed: boolean = true;
  constructor(private router: Router, private authService: AuthService) {}
  isAuthenticated() {
    return this.authService.isAuthenticated();
  }

  toggleCollapse() {
    this.isCollapsed = !this.isCollapsed;
  }

  isAuthRoute() {
    return this.router.url === '/login' || this.router.url === '/register';
  }
}
