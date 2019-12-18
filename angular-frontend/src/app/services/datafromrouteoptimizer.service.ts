import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DatafromrouteoptimizerService {

  constructor(private router: Router,
              private http: HttpClient
  ) {
  }

  async getorderdatafromrouteoptimizer(vehicleNumber) {
    // const url = '/assets/static/routeoptimizer.json';
    const url = environment.apiUrl + '/route/api/v1/routes/' + vehicleNumber + '/slot1';
    const resultFromRequest = await this.http.get(url).toPromise().then(
      result => {
        console.log(result);
        return result;
      });
    return resultFromRequest;
  }

  // async getorderdatafromrouteoptimizer(vehiclenumber: string, slot: string) {
  //   const url = environment.apiUrl + ':8091/api/v1/routes/' + vehiclenumber + '/' + slot;
  //   await this.http.get(url).toPromise().then(
  //     (result: string) => {
  //       return JSON.parse(result);
  //     });
  // }

}
