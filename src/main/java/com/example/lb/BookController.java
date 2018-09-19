package com.example.lb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookController {

    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @GetMapping("/addBook")
    public String showAddForm(Model model) {//wyswietlenie formularza dodawania
        model.addAttribute("newBook", new Book());
        return "addBook";
    }

    @PostMapping("/addBook")
    public String addBook(Book book) {
        bookRepository.save(book);//zapisanie w bazie
        return "redirect:/";
    }

    @GetMapping("/book/{id}")
    public String bookDetails(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id);
        model.addAttribute("book", book);
        return "book";

    }

    @GetMapping("/removeBook")
    public String removeTask(@RequestParam Long id) {
        Book book = bookRepository.findById(id);
        bookRepository.delete(book);
        return "redirect:/";
    }

    @GetMapping("/editBook")
    public String edit(Model model, @RequestParam Long id) {
        Book book = bookRepository.findById(id);
        model.addAttribute("book", book);
        return "editBook";
    }

    @PostMapping("/editBook")
    public String editBook(Book book,@RequestParam Long id) {
        Book edited = bookRepository.findById(id);
        edited.setTitle(book.getTitle());
        edited.setAuthor(book.getAuthor());
        edited.setCategory(book.getCategory());
        edited.setPublicationDate(book.getPublicationDate());
        edited.setDescription(book.getDescription());
        edited.setIsbn(book.getIsbn());
        bookRepository.save(edited);
        return "redirect:/";
    }
}
