import { Component, OnInit } from '@angular/core';
import {EditProfileService} from '../../services/edit-profile.service';
import {Retailerdetails} from '../../interfaces/retailerDetails';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-retailerdetails',
  templateUrl: './retailerdetails.component.html',
  styleUrls: ['./retailerdetails.component.css']
})
export class RetailerdetailsComponent implements OnInit {
  allRetailers: Array<any>;
  constructor(private retailerDetails: EditProfileService) { }

  ngOnInit() {
   this.retailerDetails.getAllRetailer().subscribe(
     retailers => {
       console.log(retailers)
       this.allRetailers = retailers;
     }

   );
  }

}
