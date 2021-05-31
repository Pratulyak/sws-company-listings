import {SwsBeService} from "../sws-be.service";

export class MockSwsBeService {
  spy: jasmine.SpyObj<SwsBeService>;

  constructor() {
    this.spy = jasmine.createSpyObj('SwsBeService', [
      'getAllCompanies',
      'getCompany'
    ]);
  }

  getSpy(): jasmine.SpyObj<SwsBeService> {
    return this.spy;
  }
}
