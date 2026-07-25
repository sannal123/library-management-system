package com.sannal.librarymanagement.service;

import com.sannal.librarymanagement.entity.Book;
import com.sannal.librarymanagement.repository.BookRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    public BookService(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }
    public  Book saveBook(Book book){
        return bookRepository.save(book);
    }
    public List<Book> getAllBooks(){
        return  bookRepository.findAll();
    }
    public  Book getBookById(Long id){
        return bookRepository.findById(id).orElse(null);
    }

    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }

    public List<Book> searchBooks(String keyword){
        return bookRepository
                .findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(
                        keyword,
                        keyword
                );
    }
    public long getTotalBooks(){
        return  bookRepository.count();
    }
    public  long getAvailableBooks(){
        return bookRepository.countByAvailable(true);
    }
    public long getUnAvailableBooks(){
        return bookRepository.countByAvailable(false);
    }

    public Page<Book> getBooks(int pageNo,
                               int pageSize,
                               String sortField,
                               String sortDirection){

        Sort sort = sortDirection.equalsIgnoreCase("asc")
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        return bookRepository.findAll(pageable);

    }
}
