import {TestBed} from '@angular/core/testing';

import {SwsBeService} from './sws-be.service';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe('SwsBeService', () => {
  let service: SwsBeService;
  let httpClient: HttpClient;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
      ],
      providers: [
        {
          provide: HttpClient, useValue: HttpClientTestingModule,
        }
      ]
    });
    service = TestBed.inject(SwsBeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should test building headers', () => {
    let response = service.buildHeaders();
    expect(response).toBeTruthy();
    expect(response.headers.get('')).toBeNull();
  });
});
