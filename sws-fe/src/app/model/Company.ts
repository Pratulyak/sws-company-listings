import {CompanyScore} from "./CompanyScore";
import {CompanyPriceClose} from "./CompanyPriceClose";

export class Company {
  public id: string;
  public name: string;
  public tickerSymbol: string;
  public exchangeSymbol: string;
  public uniqueSymbol: string;
  public dateGenerated: string;
  public securityName: string;
  public exchangeCountryIso: string;
  public listingCurrencyIso: string;
  public canonicalUrl: string;
  public uniqueSymbolSlug: string;
  public companyScore: CompanyScore;
  public swsCompanyPriceCloses: Array<CompanyPriceClose>;
  public companyVolatility: number;
}
