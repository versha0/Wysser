import { DriverLoginComponent } from './components/driver-login/driver-login.component';
import { ResetPasswordComponent } from './components/reset-password/reset-password.component';

import { TermsComponent } from './components/terms/terms.component';
import { PaymemonthlyComponent } from './components/paymemonthly/paymemonthly.component';
import { FaqComponent } from './components/faq/faq.component';
import { ContactUsComponent } from './components/contact-us/contact-us.component';
import { CareersComponent } from './components/careers/careers.component';
import { AboutUsComponent } from './components/about-us/about-us.component';

import {AuthGaurdService} from './services/auth-gaurd.service';
import {VerifyComponent} from './components/verify/verify.component';
import {HomeComponent} from './components/home/home.component';
import {SignupComponent} from './components/signup/signup.component';
import { NgModule, Component } from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {VehicleHistoryComponent} from './components/vehicle-management/vehicle-history/vehicle-history.component';
import {BookedVehiclesComponent} from './components/vehicledemandfrontend/booked-vehicles/booked-vehicles.component';
import {EditProfileComponent} from './components/edit-profile/edit-profile.component';
import {AddOrderComponent} from './components/add-order/add-order.component';
import {PageNotFoundComponent} from './components/page-not-found/page-not-found.component';
import {DriverDashboardComponent} from './components/driver-dashboard/driver-dashboard.component';
import {RetailerDashboardComponent} from './components/retailer-dashboard/retailer-dashboard.component';
import {VehicledemandfrontendComponent} from './components/vehicledemandfrontend/vehicledemandfrontend.component';
import {VehicleManagementComponent} from './components/vehicle-management/vehicle-management.component';
import {RequestsComponent} from './components/vehicle-management/requests/requests.component';
import { DriverPasswordComponent } from './components/vehicle-management/driver-password/driver-password.component';


import {ManageVehiclesComponent} from './components/vehicle-management/manage-vehicles/manage-vehicles.component';


import {from} from 'rxjs';
import {ViewProfileComponent} from './components/view-profile/view-profile.component';
import {VerifyUserComponent} from './components/verify-user/verify-user.component';
import {LoginPageComponent} from './components/login-page/login-page.component';
import { TokenFilterCheckComponent } from './components/token-filter-check/token-filter-check.component';
import {CancelreasonComponent} from './components/cancelreason/cancelreason.component';
import {SignatureComponent} from './components/signature/signature.component';
import {MaindriverdashboardComponent} from './components/maindriverdashboard/maindriverdashboard.component';
import {NavigatelocationComponent} from './components/navigatelocation/navigatelocation.component';
import {Statistic1Component} from './components/statistic1/statistic1.component';
import {RetailerdetailsComponent} from './components/retailerdetails/retailerdetails.component';
import {VehicledetailsComponent} from './components/vehicledetails/vehicledetails.component';
import {AdminfrontendComponent} from './components/adminfrontend/adminfrontend.component';
import {AdminloginComponent} from './components/adminlogin/adminlogin.component';
import { PricingComponent } from './components/pricing/pricing.component';
import { PaymeComponent } from './components/payme/payme.component';
import { VehicleCompanyProfileComponent } from './components/vehicle-company-profile/vehicle-company-profile.component';
import {PendingVehicleRequestComponent} from './components/pending-vehicle-request/pending-vehicle-request.component';
import {PendingresponsesComponent} from "./components/pendingresponses/pendingresponses.component";
import { HelpboardComponent } from './components/helpboard/helpboard.component';
import { CareerComponent } from './components/career/career.component';



const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'home/:members', component: HomeComponent},
  {path: 'terms', component: TermsComponent},
  {path: 'faq', component: FaqComponent},
  {path: 'contact-us', component: ContactUsComponent},
  {path: 'careers', component: CareersComponent},
  {path: 'about-us', component: AboutUsComponent},
  {path: 'signup', component: SignupComponent},
  {path: 'verify', component: VerifyComponent},
  { path : 'navigate', component: NavigatelocationComponent},
  { path : 'driverdashboard', component: MaindriverdashboardComponent},
  { path : 'signature', component: SignatureComponent},
  {path: 'reasons' , component: CancelreasonComponent},
  {path: 'editProfile', component: EditProfileComponent},
  // {path: 'editProfile', component: EditProfileComponent, canActivate: [AuthGaurdService]},
  {path: 'addOrder', component: AddOrderComponent},
  // {path: 'addOrder', component: AddOrderComponent, canActivate: [AuthGaurdService]},
  {path: 'driver', component: DriverLoginComponent},
  // {path: 'driver', component: DriverDashboardComponent, canActivate: [AuthGaurdService]},
  // {path: 'user', component: RetailerDashboardComponent, canActivate: [AuthGaurdService]},
  {path: 'user', component: RetailerDashboardComponent},
  {
    path: 'vehicle-management', component: VehicleManagementComponent, children: [
      {path: 'requests', component: RequestsComponent}
    ]
  },

  // {
  //   path: 'vehicle-management', component: VehicleManagementComponent, children: [
  //     {path: 'requests', component: RequestsComponent}
  //   ], canActivate: [AuthGaurdService]
  // },
  // {path: 'manage-vehicle', component: ManageVehiclesComponent, canActivate: [AuthGaurdService]},
  {path: 'manage-vehicle', component: ManageVehiclesComponent},
  {path: 'driver-password', component:DriverPasswordComponent},
  {path: 'retailerVehicleDemand', component: VehicledemandfrontendComponent},
  {path: 'help', component : HelpboardComponent},
  // {path: 'retailerVehicleDemand', component: VehicledemandfrontendComponent, canActivate: [AuthGaurdService]},
  {path: 'viewProfile', component: ViewProfileComponent},
  // {path: 'viewProfile', component: ViewProfileComponent, canActivate: [AuthGaurdService]},

  {path: 'manage-vehicle', component: ManageVehiclesComponent},

  {path: 'retailerVehicleDemand', component: VehicledemandfrontendComponent, canActivate: [AuthGaurdService]},
  {path: 'viewProfile', component: ViewProfileComponent, canActivate: [AuthGaurdService]},



  {path: 'vehicle-history', component: VehicleHistoryComponent},
  // {path: 'vehicle-history', component: VehicleHistoryComponent, canActivate: [AuthGaurdService]},


  {path: 'confirm/:token', component: VerifyUserComponent},
  {path: 'payment', component: PaymeComponent},
  {path: 'monthlypayment', component: PaymemonthlyComponent},
  {path: 'login', component: LoginPageComponent},
  {path: 'sendToken', component:TokenFilterCheckComponent },
  {path:  'resetPassword', component: ResetPasswordComponent},
  {path: 'sendToken', component: TokenFilterCheckComponent },
  // {path: 'sendToken', component:TokenFilterCheckComponent, canActivate: [AuthGaurdService] },

  { path : 'adminLogin', component: AdminloginComponent},
  { path : 'adminDashboard', component: AdminfrontendComponent},
  { path : 'vehicleDetails', component: VehicledetailsComponent},
  { path : 'retailerDetails', component: RetailerdetailsComponent},
  { path : 'pricing', component: PricingComponent},
  { path : 'statistics', component: Statistic1Component},
  { path : 'editVehicleCompanyProfile', component: VehicleCompanyProfileComponent},
  {path: 'viewPendingVehicleRequest', component: PendingVehicleRequestComponent},
  {path: 'pendingresponses', component: PendingresponsesComponent},
  {path: 'pendingresponses', component: PendingresponsesComponent},
  {path: 'careerRequest', component: CareerComponent},

  {path: '**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

export const routingModule = [HomeComponent, SignupComponent, VerifyComponent, EditProfileComponent, PageNotFoundComponent, VehicleManagementComponent, RequestsComponent, ManageVehiclesComponent, ViewProfileComponent, VehicleHistoryComponent, BookedVehiclesComponent,DriverPasswordComponent];

