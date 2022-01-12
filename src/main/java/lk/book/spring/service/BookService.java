package lk.book.spring.service;

import lk.book.spring.dto.BookDTO;

import java.util.List;

public interface BookService {
    public void saveBook(BookDTO dto);
    public void updateBook(BookDTO dto);
    public BookDTO searchBook(String id);
    public void deleteBook(String id);
    public List<BookDTO> getAllBooks();

}
