export interface PagedContent {
    content: Array<any>;
    number: number;
    totalPages: number;
    totalElements: number;
    numberOfElements: number;
    size: number;
    previousPage: boolean;
    firstPage: boolean;
    nextPage: boolean;
    lastPage: boolean;
}
