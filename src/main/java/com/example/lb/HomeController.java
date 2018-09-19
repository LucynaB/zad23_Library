package com.example.lb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    private BookRepository bookRepository;

    public HomeController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public String bookList(Model model) {

        List<Book> bookList = bookRepository.getBookList();
        model.addAttribute("books", bookList);//przekazujemy do szablonu

        return "homepage";
    }

    @GetMapping("/sort")
    public String sort(Model model, @RequestParam String order) {
        List<Book> sortedBookList = bookRepository.sort(order);
        model.addAttribute("books", sortedBookList);//przekazujemy do szablonu

        return "homepage";
    }
}
