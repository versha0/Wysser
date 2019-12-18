import {Component, ElementRef, OnInit} from '@angular/core';
import {Career} from '../../interfaces/Career';
// import {CareerService} from '../../services/career.service';
import {callbackify} from 'util';



@Component({
  selector: 'app-careers',
  templateUrl: './careers.component.html',
  styleUrls: ['./careers.component.css']
})
export class CareersComponent implements OnInit {
  name: string;
  email: string;
  role: string;
  file: string;
  temp: any;
  selectedfile = null;
  careertemp: Career = new Career();

  constructor(
              private elem: ElementRef) {
  }

  ngOnInit() {

  }

  savecareer() {
    console.log('insend');
    this.careertemp.email = this.email;
    this.careertemp.name = this.name;
    this.careertemp.role = this.role;

    // const fd = new FormData();
    // fd.append('image', this.selectedfile, this.selectedfile.name);
    //   const s = document.getElementById('file6');
    //   console.log(s);
    //
    const files = this.elem.nativeElement.querySelector('#selectfile').files;
    //   console.log(files);
    //   const formData = new FormData();
    const file = files[0];
    //   formData.append('selectfile', file, file.name);
    //   console.log('value of form data');
    //   console.log(formData);
    //   this.careertemp.file = formData;
    //   console.log(this.careertemp.file);
    //   console.log(this.careertemp.role);
    //   console.log(this.careertemp.email);
    //   console.log(this.careertemp.name);
    // console.log(this.careertemp.file);]
    const reader = new FileReader();

    reader.readAsDataURL(file);

    // function callback(result: string | ArrayBuffer) {
    //   const f = result;
    //   return f;
    //   // console.log(f);
    //
    // }


    reader.onload = () => {
      console.log(reader.result);
      this.careertemp.image = reader.result;
      // console.log(s);
      // this.temp = reader.result as string;
      // this.careertemp.file = s;
    };
    // this.careerService.sendDataOfCareer(this.careertemp).subscribe(res => {
    //   console.log(res);
    // });

  }

  onFilesSelected(event) {
    console.log(event);
    const file = event.target.files[0];
    // // if (file) {
    // const reader = new FileReader();
    //
    // // reader.onload = this.handleReaderLoaded.bind(this);
    // const f = reader.readAsBinaryString(file);
    // console.log(f);
    // const ff = reader.readAsDataURL(file);
    // console.log(ff);
  }

  // callback(file1: any) {
  //   this.careertemp.file = file1;
  // }
}

