import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Company} from "../../model/Company";
import {SwsBeService} from "../../services/sws-be.service";
import {Location} from "@angular/common";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-company-detail',
  templateUrl: './company-detail.component.html',
  styleUrls: ['./company-detail.component.css']
})
export class CompanyDetailComponent implements OnInit {

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  private companyId: any;
  public company: Company;
  public listData: MatTableDataSource<any>;
  public displayedColumns: string[] = ['price', 'date', 'dateCreated'];


  constructor(
    private route: ActivatedRoute,
    private swsBeService: SwsBeService,
    private location: Location
  ) { }

  async ngOnInit() {
    this.companyId = this.route.snapshot.paramMap.get('id');
    await this.loadCompanyDetail(this.companyId);
    this.listData = new MatTableDataSource<any>(this.company.swsCompanyPriceCloses);
    this.listData.paginator = this.paginator;
    this.listData.sort = this.sort;
  }

  private async loadCompanyDetail(companyId: string) {
    this.company = await this.swsBeService.getCompany(companyId);
  }

  goBack(){
    this.location.back();
  }
}
