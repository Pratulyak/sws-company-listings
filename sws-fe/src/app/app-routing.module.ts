import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CompaniesComponent} from "./components/companies/companies.component";
import {CompanyDetailComponent} from "./components/company-detail/company-detail.component";

const routes: Routes = [
  { path: '', component: CompaniesComponent},
  { path: 'companies', component: CompaniesComponent},
  { path: 'company/:id', component: CompanyDetailComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
