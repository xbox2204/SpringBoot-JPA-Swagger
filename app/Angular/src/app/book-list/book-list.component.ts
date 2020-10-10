import { Component, OnInit } from '@angular/core'; 
import { BookService } from '../book.service';  
import { Book } from '../book';  
import { Observable,Subject } from "rxjs";  
import {FormControl,FormGroup,Validators} from '@angular/forms';  

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {

  constructor(private bookservice:BookService) { }

  booksArray: any[] = [];  
  dtOptions: DataTables.Settings = {};  
  dtTrigger: Subject<any>= new Subject();  
  
  books: Observable<Book[]>;  
  book : Book=new Book();  
  deleteMessage=false;  
  booklist:any;  
  isupdated = false;      

  ngOnInit(): void {
    this.isupdated=false;  
    this.dtOptions = {  
      pageLength: 6,  
      stateSave:true,  
      lengthMenu:[[6, 16, 20, -1], [6, 16, 20, "All"]],  
      processing: true  
    };     
    this.bookservice.getBookList().subscribe(data =>{  
    this.books =data;  
    this.dtTrigger.next();  
    })  
  }

  deleteStudent(id: number) {  
    this.bookservice.deleteBook(id)  
      .subscribe(  
        data => {  
          console.log(data);  
          this.deleteMessage=true;  
          this.bookservice.getBookList().subscribe(data =>{  
            this.books =data  
            })  
        },  
        error => console.log(error));  
  }
  
  updateBook(id: number){  
    this.bookservice.getBook(id)  
      .subscribe(  
        data => {  
          this.booklist=data             
        },  
        error => console.log(error));  
  }  

  bookupdateform=new FormGroup({  
    student_id:new FormControl(),  
    student_name:new FormControl(),  
    student_email:new FormControl(),  
    student_branch:new FormControl()  
  });  
  
  updateStu(updstu){  
    this.book=new Book();   
   this.book.id=this.Id.value;  
   this.book.book_name=this.BookName.value;  
   this.book.author_name=this.AuthorName.value;  
   this.book.isbn=this.Isbn.value;  
   console.log(this.BookName.value);  
     
  
   this.bookservice.updateBook(this.book.id,this.book).subscribe(  
    data => {       
      this.isupdated=true;  
      this.bookservice.getBookList().subscribe(data =>{  
        this.books =data  
        })  
    },  
    error => console.log(error));  
  }  
  
  get BookName(){  
    return this.bookupdateform.get('book_name');  
  }  
  
  get AuthorName(){  
    return this.bookupdateform.get('author_name');  
  }  
  
  get Isbn(){  
    return this.bookupdateform.get('isbn');  
  }  
  
  get Id(){  
    return this.bookupdateform.get('id');  
  }  
  
  changeisUpdate(){  
    this.isupdated=false;  
  }  

}
