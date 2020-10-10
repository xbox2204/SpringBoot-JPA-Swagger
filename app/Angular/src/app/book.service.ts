import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';  
import { Observable } from 'rxjs';  

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private baseUrl = 'http://localhost:8080/vineet/';  
  constructor(private http:HttpClient) { }

  getBookList(): Observable<any> {  
    return this.http.get(`${this.baseUrl}`+'books');  
  }  

  createBook(book: object): Observable<object> {  
    return this.http.post(`${this.baseUrl}`+'books', book);  
  }  
  
  deleteBook(id: number): Observable<any> {  
    return this.http.delete(`${this.baseUrl}/books/${id}`, { responseType: 'text' });  
  }  
  
  getBook(id: number): Observable<Object> {  
    return this.http.get(`${this.baseUrl}/books/${id}`);  
  }  
  
  updateBook(id: number, value: any): Observable<Object> {  
    return this.http.post(`${this.baseUrl}/update-book/${id}`, value);  
  }  
}
