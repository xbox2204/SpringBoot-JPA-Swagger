import { Component, OnInit } from '@angular/core';
import { BookService } from '../book.service';  
import {FormControl,FormGroup,Validators} from '@angular/forms';  
import { Book } from '../book';  
@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {

  constructor(private bookservice:BookService) { }
  book : Book=new Book();  
  submitted = false;  
  ngOnInit(): void {
    this.submitted=false;  
  }

  booksaveform=new FormGroup({  
    id : new FormControl(),
    book_name:new FormControl('' , [Validators.required , Validators.minLength(5) ] ),  
    author_name:new FormControl('',[Validators.required, Validators.minLength(5)]),  
    isbn:new FormControl()  
  });  

  saveBook(saveBook){  
    this.book=new Book();     
    this.book.id=this.BookId.value;
    this.book.book_name=this.BookName.value;  
    this.book.author_name=this.AuthorName.value;  
    this.book.isbn=this.Isbn.value;  
    this.submitted = true;  
    this.save();  
  }  

  save() {  
    this.bookservice.createBook(this.book)  
      .subscribe(data => console.log(data), error => console.log(error));  
    this.book = new Book();  
  }  

  get BookId(){  
    return this.booksaveform.get('id');  
  }
  
  get BookName(){  
    return this.booksaveform.get('book_name');  
  }  
  
  get AuthorName(){  
    return this.booksaveform.get('author_name');  
  }  
  
  get Isbn(){  
    return this.booksaveform.get('isbn');  
  }  
  
  addBookForm(){  
    this.submitted=false;  
    this.booksaveform.reset();  
  }  
}
