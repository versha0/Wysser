import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {EditProfileService} from '../../services/edit-profile.service';
import * as jwt_decode from 'jwt-decode';
import { Retailerdetails } from 'src/app/interfaces/retailerDetails';
import { DecodedJwtData } from 'src/app/interfaces/decoded-jwt-data';
import { stringify } from 'querystring';
import { Observable, Observer } from 'rxjs';


@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  constructor(private httpClient: HttpClient, private router: Router, private editProfileService: EditProfileService) {
  }

  decodedData:DecodedJwtData;
  retailerObj:Retailerdetails;
  names;
  phones;
  addresss;
  companys;
  gsts;
  picUrl;
  docUrl;
  docNames;
  

  ngOnInit() {

    this.names="";
    this.phones="";
    this.addresss="";
    this.companys="";
    this.gsts="";
    this.picUrl='';
    this.docUrl='';
    this.docNames="none";


    let token = localStorage.getItem('token');

    if (token != null) {
     this.decodedData = jwt_decode(token);
    }

    this.editProfileService.getProfileFromEmail(this.decodedData.sub).subscribe((datas: any) => {
      this.retailerObj = datas;

      console.log(this.retailerObj)

      if(this.retailerObj!=null){

        this.names=this.retailerObj.fullName;
        this.phones=this.retailerObj.phoneNo;
        this.addresss=this.retailerObj.address;
        this.companys= this.retailerObj.companyName;
        this.gsts = this.retailerObj.gstIn;
        this.docNames=this.retailerObj.docName

        this.picUrl = 'data:' + this.retailerObj.profilePicType + ';base64,' + this.retailerObj.profilePic;
        this.docUrl = 'data:' + this.retailerObj.docPicType + ';base64,' + this.retailerObj.docPic;

      }

    });


 


  }


  // Methods Stats from here................................


  // fileData: File = null;
  // previewUrl:any = null;
  // fileUploadProgress: string = null;
  // uploadedFilePath: string = nuldocurl = '';l;


  // fileProgress(fileInput: any) {
  //   this.fileData = <File>fileInput.target.files[0];
  //   this.preview();


  //   this.onSelectDoc(fileInput);
  // }

  // // show preview of uploaded file in the browser
  // preview() {
  //   var mimeType = this.fileData.type;
  //   if (mimeType.match(/image\/*/) == null) {
  //     return;
  //   }
  //   var reader = new FileReader();
  //   reader.readAsDataURL(this.fileData);
  //   reader.onload = (event) => {
  //     this.previewUrl = reader.result;
  //   }
  // }

  newPic = "false";
  newDoc = "false";

  
  public picFile;

  onSelectPicture(event) {

    this.picFile = event.target.files[0];

    if (event.target.files && event.target.files[0]) {
      var reader = new FileReader();

      reader.readAsDataURL(event.target.files[0]); // read file as data url

      reader.onload = (event) => {               // called once readAsDataURL is completed
        let target: any = event.target;
        this.picUrl = target.result;
      }
    }
    
    this.newPic = "true";
    
  }




  public docFile;

  onSelectDoc(event) {

    this.docFile = event.target.files[0];

    if (event.target.files && event.target.files[0]) {
      var reader = new FileReader();

      reader.readAsDataURL(event.target.files[0]); // read file as data url

      reader.onload = (event) => {               // called once readAsDataURL is completed
        let target: any = event.target;
        this.docUrl = target.result;
      }
    }

    this.newDoc = "true";

  }


  public delete() {
    this.docUrl = null;
    this.picUrl = null;

  }






  dataURItoBlob(dataURI) {
    const byteString = window.atob(dataURI);
    const arrayBuffer = new ArrayBuffer(byteString.length);
    const int8Array = new Uint8Array(arrayBuffer);
    for (let i = 0; i < byteString.length; i++) {
      int8Array[i] = byteString.charCodeAt(i);
    }
    const blob = new Blob([int8Array], { type: 'image/jpeg' });    
    return blob;
 }



 isSaved: boolean = false;
 isAlert: boolean = false;
 alertMessage = "";



    // retailerData:Retailerdetails;
  public save(fullName, phone, address, companyName, gstIn, docName, check) {

    if (!true) {
     // alert('Please check terms and condition');
     this.isAlert=true;
     this.alertMessage='Please check terms and condition';
      this.router.navigate(['/editProfile']);
    } else if (fullName === "" || phone === "" || address === "" || gstIn === "" || companyName === "") {
     // alert('fill all the fields');
     this.alertMessage='Please fill all the details';
     this.isAlert=true;
      this.router.navigate(['/editProfile']);
    } else if (docName === "none") {
      //alert('Select a document');
      this.isAlert=true;
      this.alertMessage='Select a document type and upload';
      this.router.navigate(['/editProfile']);
    } else {
      
      var retailerData = {     
        "id" : this.decodedData.userId,
        "fullName" : fullName,
        "email" : this.decodedData.sub,
        "phoneNo" : phone,
        "address" : address,
        "gstIn" : gstIn,
        "docName" : docName,
        "companyName" : companyName
        // "profilePic":this.url,
        // "docPic":this.docurl,
      };


      
    //  convert Urls to imageFile

      // if(this.picUrl != '' && this.newPic == "false"){
      //   var contentType = this.retailerObj.profilePicType;
      //   var realData =  this.picUrl;
      //   var blob = this.dataURItoBlob(realData);

      //   this.picFile = new File([blob], "image", { type: contentType });
      // }
      // if(this.docUrl != '' && this.newDoc == "false"){
        
      //   var contentType = this.retailerObj.docPicType;
      //   var realData =  this.docUrl;
      //   var blob = this.dataURItoBlob(realData);

      //   this.docFile = new File([blob], "image", { type: contentType });


      // }
     
      // console.log(this.picUrl)
      // console.log(this.picFile)

      //////////////////////////////////////////////////


      var retailerDataString = JSON.stringify(retailerData);  //converting json to string

      let formData = new FormData();
      formData.append('profilePic', this.picFile, this.picFile.name);
      formData.append('docPic', this.docFile, this.docFile.name);
      formData.append('retailer', retailerDataString);

      this.isSaved = true;
      
      this.editProfileService.saveRetailerData(formData);

      

      this.router.navigate(['/user']);

    }

  
  }
}
