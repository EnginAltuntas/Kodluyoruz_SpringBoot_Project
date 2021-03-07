package com.ProjectX.demo.controller;

import com.ProjectX.demo.dataTransferObject.booksDTO;
import com.ProjectX.demo.domainObject.booksDO;
import com.ProjectX.demo.service.ILibraryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class LibraryController {

    @Autowired
    private ILibraryService libraryService;

    @GetMapping("/hello")
    String hello()
    {
        return "Hello World!";
    }

    @ApiOperation(value = "List all books")
    @GetMapping(path = "/books")
    public ResponseEntity<List<booksDO>> getAllBooks()
    {
        List<booksDO> allBooks = libraryService.getAllBooks();
        return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }

    @ApiOperation(value = "Add new book")
    @PostMapping(path = "/addbook",consumes = "application/json",produces="application/json")
    public ResponseEntity<booksDO> addBook(@RequestBody booksDO booksDO)
    {
        booksDO newbook = libraryService.addBook(booksDO);
        return new ResponseEntity<>(newbook, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete book by id")
    @DeleteMapping(path = "/book/{book_id}")
    public ResponseEntity<String> deleteBook(@PathVariable(value = "book_id")Long id)
    {
        libraryService.deleteBook(id);
        return new ResponseEntity<>("Book id: "+id+" is deleted",HttpStatus.OK);
    }
    @ApiOperation(value = "Update book information")
    @PutMapping(path = "/putbook",consumes = "application/json",produces = "application/json")
    public ResponseEntity<booksDTO> updateBook(@RequestBody booksDO book)
    {
        booksDTO updateBook = libraryService.updateBook(book);
        return new ResponseEntity<>(updateBook,HttpStatus.CREATED);
    }


}
