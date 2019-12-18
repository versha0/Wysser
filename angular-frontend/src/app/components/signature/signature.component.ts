import {Component, OnInit, ViewChild} from '@angular/core';
import {SignatureService} from '../../services/signature.service';
import {InteractionService} from '../../services/interaction.service';
import {Router} from '@angular/router';
import {SignaturePad} from 'angular2-signaturepad/signature-pad';
import {SignatureDetails} from '../../interfaces/signature';

@Component({
  selector: 'app-signature',
  templateUrl: './signature.component.html',
  styleUrls: ['./signature.component.css']
})
export class SignatureComponent implements OnInit {

  constructor(private signatureservice: SignatureService,
              private interactionservice: InteractionService,
              private router: Router) { }
  @ViewChild('sp', {static: false}) signaturePad: SignaturePad;

  public signaturePadOptions = {
    minWidth: 2,
    backgroundColor: 'rgb(255,255,255)',
    penColor: 'rgb(66,133,244)',
    canvasWidth: 450,
    canvasHeight: 150
  };
  ngOnInit() {
  }
  drawComplete() {
    // this.interactionservice.sendMessage('hi');
    const signaturedetails = new SignatureDetails();
    const s = document.getElementById('sp');
    console.log(s);
    const signaturePad = new SignaturePad(this.signaturePad.queryPad());
    const base64 = this.signaturePad.toDataURL('image/png', 0.5);
    signaturedetails.signatureimage = base64;
    // signaturedetails.orderid = '01234';
    console.log(signaturedetails.signatureimage);
    console.log(base64);
    const blob = this.base64toblob(base64);
    console.log(blob);
    this.signatureservice.sendSignatureRequest(signaturedetails).subscribe(data => {
      console.log(data);
    });
    this.router.navigateByUrl('driverdashboard');
  }

  base64toblob(base64) {
    const byteString = atob(base64.split(',')[1]);
    const mimeString = base64.split(',')[0].split(':')[1].split(':')[0];
    const byteNumbers = new Array(byteString.length);
    for (let i = 0; i < byteString.length; i++) {
      byteNumbers[i] = byteString.charAt(i);

    }
    const ia = new Uint8Array(byteNumbers);
    return new Blob([ia], {type: mimeString});
  }
}
