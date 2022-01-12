package lk.book.spring.controller;


import lk.book.spring.dto.BookDTO;
import lk.book.spring.service.BookService;
import lk.book.spring.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@CrossOrigin
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity addBook(@RequestBody BookDTO dto){
        bookService.saveBook(dto);
        StandardResponse success = new StandardResponse(200,"Success",null);
        return new ResponseEntity(success, HttpStatus.OK);
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity deleteBook(@RequestParam String id){
        bookService.deleteBook(id);
        StandardResponse success = new StandardResponse(200,"Success",null);
        return new ResponseEntity(success, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateBook(@RequestBody BookDTO dto) {
        bookService.updateBook(dto);
        return new ResponseEntity(new StandardResponse(200, "Success", null), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity searchBook(@PathVariable String id){
        BookDTO bookDTO = bookService.searchBook(id);
        StandardResponse success = new StandardResponse(200,"Success", bookDTO);
        return new ResponseEntity(success, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAllCBooks(){
        List<BookDTO> allCustomers = bookService.getAllBooks();
        StandardResponse success = new StandardResponse(200,"Success",allCustomers);
        return new ResponseEntity(success, HttpStatus.OK);
    }

}
