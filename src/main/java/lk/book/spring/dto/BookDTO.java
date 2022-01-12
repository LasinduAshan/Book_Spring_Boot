package lk.book.spring.dto;

import lombok.Data;

@Data
public class BookDTO {
    private String isbn;
    private String name;
    private String author;
    private String category;

}
