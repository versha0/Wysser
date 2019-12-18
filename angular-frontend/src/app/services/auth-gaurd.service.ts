import {Injectable} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';
import { LoginAuthService } from './login-auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGaurdService implements CanActivate {

  constructor(private router: Router,private loginAuthService: LoginAuthService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

    // if (this.loginAuthService.isTokenValid())
    // return true;

    if (this.loginAuthService.isTokenExpired())
      return true;

    this.router.navigate(['login']);
    return false;


  }

}
