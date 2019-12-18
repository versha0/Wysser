import {Injectable} from '@angular/core';
import {HttpInterceptor, HttpRequest, HttpHandler} from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class BasicAuthHtppInterceptorService implements HttpInterceptor {

  constructor() {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler) {

    // if (localStorage.getItem('token')) {
      let TokenizedReq = req.clone({
        setHeaders: {
          Authorization: 'Token '+localStorage.getItem('token')
        }
      })
  //  }

    return next.handle(TokenizedReq);

  }
}
