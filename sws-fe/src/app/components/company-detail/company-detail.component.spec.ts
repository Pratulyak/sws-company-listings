import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompanyDetailComponent } from './company-detail.component';
import {CompanyPriceClose} from "../../model/CompanyPriceClose";
import {DatePipe} from "@angular/common";
import {CompanyScore} from "../../model/CompanyScore";
import {Company} from "../../model/Company";
import {MockSwsBeService} from "../../services/mocks/MockSwsBeService";
import {SwsBeService} from "../../services/sws-be.service";
import {HttpClient} from "@angular/common/http";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {ActivatedRoute, convertToParamMap} from "@angular/router";
import {Location} from "@angular/common";



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
  },
  {
    price: 13,
    date: "2020-01-03",
    dateCreated: new DatePipe("2020-01-03"),
    companyId: "id",
  },
]

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

describe('CompanyDetailComponent', () => {
  let component: CompanyDetailComponent;
  let fixture: ComponentFixture<CompanyDetailComponent>;
  let mockSwsBeService: MockSwsBeService;
  let activatedRouteMock;
  let location: Location;

  beforeEach(async () => {
    mockSwsBeService = new MockSwsBeService();
    activatedRouteMock = {
      snapshot: {
        paramMap: convertToParamMap({
          id: companyA.id,
        })
      }
    };

    location = jasmine.createSpyObj('Location', ['back']);


    await TestBed.configureTestingModule({
      declarations: [ CompanyDetailComponent ],
      providers: [
        {
          provide: SwsBeService, useValue: mockSwsBeService.getSpy()
        },
        {
          provide: HttpClient, useValue: HttpClientTestingModule
        },
        {
          provide: ActivatedRoute, useValue: activatedRouteMock
        },
        {
          provide: Location, useValue: location
        }
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CompanyDetailComponent);
    component = fixture.componentInstance;
    location = TestBed.get(Location);
    fixture.detectChanges();
  });

  it('should test if the component was created', async () => {
    mockSwsBeService.getSpy().getCompany.and.returnValue(Promise.resolve(companyA));

    await component.ngOnInit();
    await fixture.whenStable();
    fixture.detectChanges();
    expect(component).toBeTruthy();
    expect(component.company).toEqual(companyA);
  });

  it('should test if the location routing works', async () => {
    mockSwsBeService.getSpy().getCompany.and.returnValue(Promise.resolve(companyA));

    await component.ngOnInit();
    await fixture.whenStable();
    fixture.detectChanges();

    component.goBack();
    expect(location.back).toHaveBeenCalled();
  });
});
