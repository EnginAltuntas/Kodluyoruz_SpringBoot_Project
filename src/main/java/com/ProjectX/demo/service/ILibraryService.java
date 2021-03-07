package com.ProjectX.demo.service;

import com.ProjectX.demo.dataTransferObject.booksDTO;
import com.ProjectX.demo.domainObject.booksDO;

import java.util.List;

public interface ILibraryService {

    List<booksDO> getAllBooks();
    booksDO addBook(booksDO newbook);
    void deleteBook(Long id);
    booksDTO updateBook(booksDO book);

}
