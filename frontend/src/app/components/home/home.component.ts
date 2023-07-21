import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  navigateToSection(section: string) {
    window.location.hash = '';
    window.location.hash = section;
}
}
