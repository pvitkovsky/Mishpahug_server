import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AddressConnection} from '../Models/Connections/AddressConnection';

@Injectable({
  providedIn: 'root'
})
export class AddressService {

  constructor(private http: HttpClient){
  }

  getAddressPart (options: AddressConnection) : Observable<String[]> {
    return this.http.get<String[]>(options.valueOf());
  }
}

