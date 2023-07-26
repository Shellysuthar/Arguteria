import { OrderComponent } from './components/order/order.component';
import { MenuComponent } from './components/menu/menu.component';
import { AuthGuard } from './guard/auth.guard';
// import { AnalyticsComponent } from './components/analytics/analytics.component';
import { HomeComponent } from './components/home/home.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './auth/register/register.component';
import { LoginComponent } from './auth/login/login.component';
import { NgModule } from '@angular/core';
import { CartComponent } from './components/cart/cart.component';
import { PollComponent } from './components/poll/poll.component';
import { AddPollComponent } from './components/add-poll/add-poll.component';



const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'menu', component: MenuComponent,canActivate: [AuthGuard]},
  { path: 'poll', component: PollComponent},
  { path: 'orders', component: OrderComponent},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
