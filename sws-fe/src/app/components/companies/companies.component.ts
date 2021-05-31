import {Component, OnInit, ViewChild} from '@angular/core';
import {Company} from "../../model/Company";
import {PagedContent} from "../../model/response";
import {MatSort, Sort} from "@angular/material/sort";
import {Router} from "@angular/router";
import {SwsBeService} from "../../services/sws-be.service";
import {MatTableDataSource, MatTableModule} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-companies',
  templateUrl: './companies.component.html',
  styleUrls: ['./companies.component.css']
})
export class CompaniesComponent implements OnInit {

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  public listData: MatTableDataSource<any>;

  public companiesList: Company[];
  public displayedColumns: string[] = ['name', 'uniqueSymbol', 'exchangeSymbol', 'companyScore', 'volatility', 'closePrice', 'action']

  constructor(
    private companyService: SwsBeService,
    private router: Router) {
  }

  async ngOnInit() {
    if (this.companiesList == null) {
      await this.loadCompanies();
      this.listData = new MatTableDataSource<any>(this.companiesList);
      this.listData.paginator = this.paginator;
      this.listData.sort = this.sort;
      this.listData.sortingDataAccessor = (item, property) => {
        switch (property) {
          case 'companyScore':
            return item.companyScore.total;
          case 'volatility':
            return item.companyVolatility.toString();
          default:
            return item[property];
        }
      }
    }
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.listData.filter = filterValue.trim().toLowerCase();

    if (this.listData.paginator) {
      this.listData.paginator.firstPage();
    }
  }

  async loadCompanies() {
    let response: PagedContent = await this.companyService.getAllCompanies();
    this.companiesList = response.content;
    this.companiesList.forEach(function (value) {
      value.swsCompanyPriceCloses.sort((a) => {
        return -new Date(a.date).getDate();
      });
    });
    this.companiesList.forEach(function (value) {
      value.swsCompanyPriceCloses.sort((a) => {
        return a.price;
      });
      value.companyVolatility = value.swsCompanyPriceCloses[0].price - value.swsCompanyPriceCloses[value.swsCompanyPriceCloses.length - 1].price;
    });
  }

  seeCompany(event: Company) {
    this.router.navigateByUrl('company/' + event.id);
  }
}

