package lk.book.spring.service.impl;

import lk.book.spring.dto.BookDTO;
import lk.book.spring.entity.Book;
import lk.book.spring.repo.BookRepo;
import lk.book.spring.service.BookService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepo repo;

    @Autowired
    ModelMapper mapper;

    @Override
    public void saveBook(BookDTO dto){

        if (!repo.existsById(dto.getIsbn())){
            Book book = mapper.map(dto, Book.class);
            repo.save(book);
        }else {
            throw new RuntimeException("Book already exist");
        }


    }

    @Override
    public void updateBook(BookDTO dto) {
        if (repo.existsById(dto.getIsbn())) {
            Book c = mapper.map(dto, Book.class);
            repo.save(c);
        } else {
            throw new RuntimeException("No such book for update..!");
        }
    }

    @Override
    public BookDTO searchBook(String id) {
        Optional<Book> customer =repo.findById(id);
        if (customer.isPresent()){
           return mapper.map(customer.get(), BookDTO.class);
        }else {
            throw new RuntimeException("No Book for ID "+ id);
        }
    }

    @Override
    public void deleteBook(String id) {
        if (repo.existsById(id)){
            repo.deleteById(id);
        }else {
            throw new RuntimeException("No Book for the Delete ID "+ id);
        }

    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> all = repo.findAll();
        return mapper.map(all, new TypeToken<List<BookDTO>>(){
        }.getType());
    }
}
