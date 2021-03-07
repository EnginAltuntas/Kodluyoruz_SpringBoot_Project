package com.ProjectX.demo.service;

import com.ProjectX.demo.dataTransferObject.booksDTO;
import com.ProjectX.demo.domainObject.booksDO;
import com.ProjectX.demo.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryService implements ILibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    @Override
    public List<booksDO> getAllBooks() {

        return libraryRepository.findAll();
    }

    @Override
    public booksDO addBook(booksDO newbook) {
        return libraryRepository.save(newbook);
    }

    @Override
    public void deleteBook(Long id) {
        Optional<booksDO> currentBook = libraryRepository.findById(id);
        if(currentBook.isPresent())
        {
            libraryRepository.deleteById(id);
        }

    }

    @Override
    public booksDTO updateBook(booksDO book) {
        long book_id = book.getId();
        Optional<booksDO> currentBook = libraryRepository.findById(book_id);
        if(currentBook.isPresent())
        {
            currentBook.get().setBook_name(book.getBook_name());
            currentBook.get().setAuthor_name(book.getAuthor_name());
            currentBook.get().setNumberOfPages(book.getNumberOfPages());
            libraryRepository.save(currentBook.get());

            booksDTO booksDTO = new ModelMapper().map(currentBook.get(),booksDTO.class);
            return booksDTO;
        }

        return null;
    }


}
