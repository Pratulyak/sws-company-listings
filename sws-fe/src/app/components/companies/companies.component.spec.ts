import {ComponentFixture, TestBed} from '@angular/core/testing';

import {CompaniesComponent} from './companies.component';
import {MockSwsBeService} from "../../services/mocks/MockSwsBeService";
import {SwsBeService} from "../../services/sws-be.service";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {Company} from "../../model/Company";
import {CompanyPriceClose} from "../../model/CompanyPriceClose";
import {DatePipe} from "@angular/common";
import {CompanyScore} from "../../model/CompanyScore";
import {PagedContent} from "../../model/response";
import {RouterTestingModule} from "@angular/router/testing";


const swsCompanyPriceClose: CompanyPriceClose[] = [
  {
    price: 10,
    date: "2020-01-01",
    dateCreated: new DatePipe("2020-01-01"),
    companyId: "id",

  },
  {
    price: 12,
    date: "2020-01-02",
    dateCreated: new DatePipe("2020-01-02"),
    companyId: "id",
  }]

const companyScore: CompanyScore = {
  companyId: "id",
  dateGenerated: "2020-01-01",
  dividend: "1",
  future: "2",
  health: "3",
  management: "4",
  past: "0",
  value: "3",
  misc: "7",
  total: "2",
  sentence: "test"
}

const companyA = new Company();
companyA.id = "id";
companyA.name = "companyA";
companyA.swsCompanyPriceCloses = swsCompanyPriceClose;
companyA.companyScore = companyScore;
companyA.exchangeSymbol = "ASX:AAP"

const validCompanyListResponse: PagedContent = {
  firstPage: false,
  lastPage: false,
  nextPage: false,
  number: 1,
  numberOfElements: 1,
  previousPage: false,
  size: 1,
  totalElements: 1,
  totalPages: 1,
  content: new Array<Company>(companyA)
}

describe('CompaniesComponent', () => {
  let component: CompaniesComponent;
  let fixture: ComponentFixture<CompaniesComponent>;
  let mockSwsBeService: MockSwsBeService;
  let router: Router;

  beforeEach(async () => {
    mockSwsBeService = new MockSwsBeService();

    await TestBed.configureTestingModule({
      declarations: [CompaniesComponent],
      imports: [
        HttpClientTestingModule,
        RouterTestingModule.withRoutes([])
      ],
      providers: [
        {provide: SwsBeService, useValue: mockSwsBeService.getSpy()}
      ]
    })
      .compileComponents();

    router = TestBed.get(Router);
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CompaniesComponent);
    component = fixture.componentInstance;
    router = TestBed.get(Router);
    fixture.detectChanges();
  });

  it('should create a component to verify init was called', () => {
    expect(component).toBeTruthy();
    expect(component.companiesList).toBeUndefined();
    expect(mockSwsBeService.getSpy().getAllCompanies).toHaveBeenCalled();
  });

  it('should create a component to verify init was called with a valid response from the service', async () => {
    mockSwsBeService.getSpy().getAllCompanies.and.returnValue(Promise.resolve(validCompanyListResponse));

    await component.ngOnInit();
    await fixture.whenStable();
    fixture.detectChanges();

    expect(component.companiesList).toEqual(validCompanyListResponse.content);
    expect(component.listData.data).toEqual(validCompanyListResponse.content);
  });

  it('should try to redirect to the company id', async () => {
    mockSwsBeService.getSpy().getAllCompanies.and.returnValue(Promise.resolve(validCompanyListResponse));
    const navigateSpy = spyOn((<any>component).router, 'navigateByUrl');

    await component.ngOnInit();
    await fixture.whenStable();
    fixture.detectChanges();

    component.seeCompany(companyA);
    expect(router.navigateByUrl).toHaveBeenCalledOnceWith('company/' + companyA.id);
  });
});
