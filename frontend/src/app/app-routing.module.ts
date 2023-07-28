import { OrderComponent } from './components/order/order.component';
import { MenuComponent } from './components/menu/menu.component';
import { AuthGuard } from './guard/auth.guard';
// import { AnalyticsComponent } from './components/analytics/analytics.component';
import { HomeComponent } from './components/home/home.component';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './auth/register/register.component';
import { LoginComponent } from './auth/login/login.component';
import { NgModule } from '@angular/core';
import { PollComponent } from './components/poll/poll.component';
import { AddPollComponent } from './components/add-poll/add-poll.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'dashboard', component: AddPollComponent,canActivate: [AuthGuard]},
  { path: 'menu', component: MenuComponent,canActivate: [AuthGuard]},
  { path: 'poll', component: PollComponent,canActivate: [AuthGuard]},
  { path: 'orders', component: OrderComponent,canActivate: [AuthGuard]},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
