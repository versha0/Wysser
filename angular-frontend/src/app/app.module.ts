import { PricingComponent } from './components/pricing/pricing.component';
import { GetlistService } from './services/getlist.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components//home/home.component';
import { SignupComponent } from './components/signup/signup.component';
import { VerifyComponent } from './components/verify/verify.component';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatFormFieldModule } from '@angular/material/form-field';
import { A11yModule } from '@angular/cdk/a11y';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { PortalModule } from '@angular/cdk/portal';
import { ScrollingModule } from '@angular/cdk/scrolling';
import { CdkStepperModule } from '@angular/cdk/stepper';
import { CdkTableModule } from '@angular/cdk/table';
import { CdkTreeModule } from '@angular/cdk/tree';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatBadgeModule } from '@angular/material/badge';
import { MatBottomSheetModule } from '@angular/material/bottom-sheet';
import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatChipsModule } from '@angular/material/chips';
import { MatStepperModule } from '@angular/material/stepper';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDialogModule } from '@angular/material/dialog';
import { MatDividerModule } from '@angular/material/divider';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import { MatNativeDateModule, MatRippleModule } from '@angular/material/core';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatSliderModule } from '@angular/material/slider';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { MatTabsModule } from '@angular/material/tabs';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatTreeModule } from '@angular/material/tree';
import { AddOrderComponent } from './components/add-order/add-order.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { DriverDashboardComponent } from './components/driver-dashboard/driver-dashboard.component';
import { RemainingVolumePipe } from './remaining-volume.pipe';
import { RetailerDashboardComponent } from './components/retailer-dashboard/retailer-dashboard.component';
import { ChartsModule } from 'ng2-charts';
// import { RatecardpopupComponent } from './ratecardpopup/ratecardpopup.component';


import { VehicledemandfrontendComponent } from './components/vehicledemandfrontend/vehicledemandfrontend.component';
import { FormsModule } from '@angular/forms';
import { VehicleManagementComponent } from './components/vehicle-management/vehicle-management.component';
import { RequestsComponent } from './components/vehicle-management/requests/requests.component';


import { NgAlertModule } from '@theo4u/ng-alert';


import { ReactiveFormsModule } from '@angular/forms';
import { ManageVehiclesComponent } from './components/vehicle-management/manage-vehicles/manage-vehicles.component';


import { ViewProfileComponent } from './components/view-profile/view-profile.component';
import { TimeSlotPipe } from './time-slot.pipe';
import { VehicleHistoryComponent } from './components/vehicle-management/vehicle-history/vehicle-history.component';
import { from } from 'rxjs';
import { BookedVehiclesComponent } from './components/vehicledemandfrontend/booked-vehicles/booked-vehicles.component';
import { ViewVehicleComponent } from './components/vehicledemandfrontend/view-vehicle/view-vehicle.component';
import { VerifyUserComponent } from './components/verify-user/verify-user.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { BasicAuthHtppInterceptorService } from './services/basic-auth-htpp-interceptor.service';
import { LoginAuthService } from './services/login-auth.service';
import { AuthGaurdService } from './services/auth-gaurd.service';
import { TokenFilterCheckService } from './services/token-filter-check.service';
import { EditProfileService } from './services/edit-profile.service';
import { TokenFilterCheckComponent } from './components/token-filter-check/token-filter-check.component';
import { CanceldialogueComponent } from './components/canceldialogue/canceldialogue.component';
import { CancelreasonComponent } from './components/cancelreason/cancelreason.component';
import { MaindriverdashboardComponent } from './components/maindriverdashboard/maindriverdashboard.component';
import { NavigatelocationComponent } from './components/navigatelocation/navigatelocation.component';
import { SignatureComponent } from './components/signature/signature.component';
import { SignaturePadModule } from 'angular2-signaturepad';
import { SignatureService } from './services/signature.service';
import { InteractionService } from './services/interaction.service';
import { AdminloginComponent } from './components/adminlogin/adminlogin.component';
import { AdminfrontendComponent } from './components/adminfrontend/adminfrontend.component';
import { RetailerdetailsComponent } from './components/retailerdetails/retailerdetails.component';
import { Statistic1Component } from './components/statistic1/statistic1.component';
import { VehicledetailsComponent } from './components/vehicledetails/vehicledetails.component';
import {DatePipe} from '@angular/common';
// import { PaymentComponent } from './components/payment/payment.component';
import { PaymeComponent } from './components/payme/payme.component';
import { PaymemonthlyComponent } from './components/paymemonthly/paymemonthly.component';
import { VehicleCompanyProfileComponent } from './components/vehicle-company-profile/vehicle-company-profile.component';
import { TermsComponent } from './components/terms/terms.component';
import { ResetPasswordComponent } from './components/reset-password/reset-password.component';
import { PendingVehicleRequestComponent } from './components/pending-vehicle-request/pending-vehicle-request.component';
import { FaqComponent } from './components/faq/faq.component';
import { ContactUsComponent } from './components/contact-us/contact-us.component';
import { DriverPasswordComponent } from './components/vehicle-management/driver-password/driver-password.component';

import { PendingresponsesComponent } from './components/pendingresponses/pendingresponses.component';
import { ContactusreplypopupComponent } from './components/contactusreplypopup/contactusreplypopup.component';

import { DriverLoginComponent } from './components/driver-login/driver-login.component';
import { TeamComponent } from './components/home/team/team.component';
import { PartnersComponent } from './components/home/partners/partners.component';
import { MapComponent } from './components/mapcomponent/mapcomponent.component';
import { CareersComponent } from './components/careers/careers.component';
import { AddDeveloperComponent } from './components/careers/add-developer/add-developer.component';

import { CareerComponent } from './components/career/career.component';

import { AboutUsComponent } from './components/about-us/about-us.component';
import { HelpboardComponent } from './components/helpboard/helpboard.component';



@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SignupComponent,
    VerifyComponent,
    AddOrderComponent,
    EditProfileComponent,
    PageNotFoundComponent,
    DriverDashboardComponent,
    RemainingVolumePipe,
    RetailerDashboardComponent,
    VehicledemandfrontendComponent,
    // RatecardpopupComponent,
    VehicleManagementComponent,
    ViewVehicleComponent,
    BookedVehiclesComponent,

    RequestsComponent,
    PricingComponent,
    ManageVehiclesComponent,
    ViewProfileComponent,
    TimeSlotPipe,
    VerifyUserComponent,
    VehicleHistoryComponent,

    NavbarComponent,
    FooterComponent,
    LoginPageComponent,
    TokenFilterCheckComponent,
    CanceldialogueComponent,
    CancelreasonComponent,
    MaindriverdashboardComponent,
    NavigatelocationComponent,
    SignatureComponent,
    AdminloginComponent,
    AdminfrontendComponent,
    RetailerdetailsComponent,
    Statistic1Component,
    VehicledetailsComponent,
    PricingComponent,
    // PaymentComponent,
    PaymeComponent,
    PaymemonthlyComponent,
    VehicleCompanyProfileComponent,
    TermsComponent,
    ResetPasswordComponent,
    PendingVehicleRequestComponent,
    FaqComponent,
    ContactUsComponent,
    DriverPasswordComponent,

    PendingresponsesComponent,
    ContactusreplypopupComponent,

    DriverLoginComponent,
    TeamComponent,
    PartnersComponent,
    MapComponent,
    CareersComponent,
    AddDeveloperComponent,
    CareerComponent,
    AboutUsComponent,
    HelpboardComponent

  ],

  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,

    BrowserModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatAutocompleteModule,
    MatCheckboxModule,
    HttpClientModule,
    ReactiveFormsModule,


    A11yModule,
    CdkStepperModule,
    CdkTableModule,
    CdkTreeModule,
    DragDropModule,
    MatAutocompleteModule,
    MatBadgeModule,
    MatBottomSheetModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatCheckboxModule,
    MatChipsModule,
    MatStepperModule,
    MatDatepickerModule,
    MatDialogModule,
    MatDividerModule,
    MatExpansionModule,
    MatGridListModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatNativeDateModule,
    MatPaginatorModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    MatRippleModule,
    MatSelectModule,
    MatSidenavModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatSnackBarModule,
    MatSortModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule,
    MatTreeModule,
    PortalModule,
    ScrollingModule,

    ChartsModule,
    NgAlertModule,

    MatCardModule,
    MatCheckboxModule,
    MatChipsModule,
    MatStepperModule,
    MatDatepickerModule,
    MatDialogModule,
    MatDividerModule,
    MatExpansionModule,
    MatGridListModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatNativeDateModule,
    MatPaginatorModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    MatRippleModule,
    SignaturePadModule


  ],
  entryComponents: [ViewVehicleComponent, BookedVehiclesComponent, CanceldialogueComponent, ContactusreplypopupComponent],
  providers: [GetlistService, LoginAuthService, AuthGaurdService, EditProfileService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: BasicAuthHtppInterceptorService,
      multi: true
    },
    SignatureService, InteractionService, DatePipe

  ],

  bootstrap: [AppComponent]
})
export class AppModule {
}
