import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class SwsBeService {
  private COMPANY_ENDPOINT = "/company";
  private COMPANY_DETAIL_ENDPOINT = "/company/:id";
  constructor(private httpClient: HttpClient) { }


  buildHeaders(queryParams?: any) {
    const headers = {
    };

    let options = {
      headers: new HttpHeaders(headers),
    };

    return options;
  }

  async getAllCompanies(): Promise<any> {

    let url = environment.serverUrl + this.COMPANY_ENDPOINT;
    let options = this.buildHeaders();

    return this.httpClient.get(url, options).toPromise();
  }

  async getCompany(companyId: string): Promise<any> {
    let url = environment.serverUrl + this.COMPANY_DETAIL_ENDPOINT.replace(":id", companyId);
    return this.httpClient.get(url, this.buildHeaders()).toPromise();
  }
}
