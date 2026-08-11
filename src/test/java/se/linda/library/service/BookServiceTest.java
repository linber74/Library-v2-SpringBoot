package se.linda.library.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.linda.library.exception.ItemNotFoundException;
import se.linda.library.model.entity.Book;
import se.linda.library.model.enums.BookFormat;
import se.linda.library.model.enums.FanficType;
import se.linda.library.repository.BookRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService bookService;

    Book testBook;
    Book testBook2;
    Book testBook3;
    List<Book> testAllBooks;

    @BeforeEach
    public void setUp() {
        testBook = new Book();
        testBook.setId(1L);
        testBook.setTitle("The Hobbit");
        testBook.setAuthor(List.of("Tolkien"));
        testBook.setBookFormat(BookFormat.PRINT_BOOK);

        testBook2 = new Book();
        testBook2.setId(2L);
        testBook2.setTitle("Harry Potter");
        testBook2.setAuthor(List.of("J.K. Rowling"));
        testBook2.setBookFormat(BookFormat.EBOOK);

        testBook3 = new Book();
        testBook3.setId(3L);
        testBook3.setTitle("A Fanfic Story");
        testBook3.setBookFormat(BookFormat.FANFICTION);
        testBook3.setFanficType(FanficType.CANON);
        testBook3.setFandom(List.of("Harry Potter"));

        testAllBooks = List.of(testBook, testBook2, testBook3);
    }

    @Test
    public void save_shouldReturnSavedBook() {
        when(bookRepository.save(any(Book.class))).thenReturn(testBook);

        Book result = bookService.save(testBook);

        assertEquals(testBook.getTitle(),  result.getTitle());
        verify (bookRepository).save(testBook);
    }

    @Test
    public void save_shouldThrowException_whenBookIsNull() {
        assertThrows(IllegalArgumentException.class, () -> bookService.save(null));
    }

    @Test
    public void getBookById_shouldReturnBook_whenBookExists() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(testBook));

        Book result = bookService.getById(1L);

        assertEquals(testBook.getTitle(), result.getTitle());
    }

    @Test
    public void getBookById_shouldThrowException_whenBookNotFound() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class,
                () -> bookService.getById(99L));
    }

    @Test
    public void deleteById_shouldDeleteBook_whenBookExists() {
        when(bookRepository.existsById(1L)).thenReturn(true);

        bookService.deleteById(1L);
        verify(bookRepository).deleteById(1L);
    }

    @Test
    public void deleteById_shouldThrowException_whenBookNotFound() {
        when(bookRepository.existsById(99L)).thenReturn(false);

        assertThrows(ItemNotFoundException.class,
                () -> bookService.deleteById(99L));
        verify(bookRepository, never()).deleteById(any());
    }

    @Test
    public void searchByAuthor_shouldReturnBooks_whenAuthorIsValid() {

        when(bookRepository.findByAuthorContainingIgnoreCase("Tolkien"))
                .thenReturn(List.of(testBook));

        List<Book> result = bookService.searchByAuthor("Tolkien");

        assertEquals(1, result.size());
    }

    @Test
    public void searchByAuthor_shouldThrowException_whenAuthorIsBlank() {
        assertThrows(IllegalArgumentException.class,
                () -> bookService.searchByAuthor(""));
    }

    @Test
    public void searchByBookFormat_shouldReturnBooks_whenBookFormatIsValid() {

        when(bookRepository.findByBookFormat(BookFormat.EBOOK))
        .thenReturn(List.of(testBook2));

        List<Book> result = bookService.searchByBookFormat(BookFormat.EBOOK);
        assertEquals(1, result.size());
    }

    @Test
    public void searchByBookFormat_shouldThrowException_whenBookFormatIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> bookService.searchByBookFormat(null));
    }

    @Test
    public void searchByFanficType_shouldReturnBooks_whenFanficTypeIsValid() {

        when(bookRepository.findByFanficType(FanficType.CANON))
        .thenReturn(List.of(testBook3));

        List<Book> result = bookService.searchByFanficType(FanficType.CANON);
        assertEquals(1, result.size());
    }

    @Test
    public void searchByFanficType_shouldThrowException_whenFanficTypeIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> bookService.searchByFanficType(null));
    }

    @Test
    public void searchByFandom_shouldReturnBooks_whenFandomIsValid() {

        when(bookRepository.findByFandomContainingIgnoreCase("Harry Potter"))
                .thenReturn(List.of(testBook3));

        List<Book> result = bookService.searchByFandom("Harry Potter");
        assertEquals(1, result.size());
    }

    @Test
    public void searchByFandom_shouldThrowException_whenFandomIsBlank() {

        assertThrows(IllegalArgumentException.class,
                () -> bookService.searchByFandom(""));
    }
}
