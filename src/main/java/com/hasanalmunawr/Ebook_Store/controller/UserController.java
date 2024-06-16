package com.hasanalmunawr.Ebook_Store.controller;


import com.hasanalmunawr.Ebook_Store.dto.ResetPasswordRequest;
import com.hasanalmunawr.Ebook_Store.dto.response.EbookResponse;
import com.hasanalmunawr.Ebook_Store.service.AuthService;
import com.hasanalmunawr.Ebook_Store.service.CustomerService;
import com.hasanalmunawr.Ebook_Store.user.UserEntity;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final CustomerService customerService;
    private final AuthService authService;


    @PostMapping("/send-update/password")
    public RedirectView requestUpdatePassword(
            @AuthenticationPrincipal UserEntity currentUser
    ) throws MessagingException {

        authService.updatePassword(currentUser);

        return new RedirectView("/");
    }

    @PutMapping("/update/password")
    public void updatePassword(
            @AuthenticationPrincipal UserEntity currentUser,
            @RequestParam("code") Integer code,
            @RequestBody ResetPasswordRequest request
    ) throws MessagingException {
        authService.changePassword(currentUser, code, request);
    }

    @GetMapping(path = "/{isbn}")
    public ResponseEntity<EbookResponse> getEbook(
            @PathVariable String isbn,
            @AuthenticationPrincipal UserEntity currentUser
    ) {
        return ResponseEntity.ok(customerService.searchEbookByIsbn(isbn));
    }


    @GetMapping(path = "/filterByPrice")
    public ResponseEntity<Page<EbookResponse>> FilterEbookByPriceRange(
            @RequestParam("min") double min,
            @RequestParam("max") double max,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @AuthenticationPrincipal UserEntity user
    ) {
        return ResponseEntity.ok(customerService.FilterEbookByPriceRange(min, max, page, size));
    }

    @GetMapping(path = "/filterByTitle")
    public ResponseEntity<Page<EbookResponse>> FilterEbookByTitle(
            @RequestParam("title") String title,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @AuthenticationPrincipal UserEntity user
    ) {
        return ResponseEntity.ok(customerService.FilterEbookByTitle(title, page, size));
    }

    @GetMapping(path = "/filterByAuthor")
    public ResponseEntity<Page<EbookResponse>> FilterEbookByAuthor(
            @RequestParam("author") String author,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @AuthenticationPrincipal UserEntity user
    ) {
        return ResponseEntity.ok(customerService.FilterEbookByAuthor(author, page, size));
    }

    @Cacheable(value = "booksAsc",  key = "#page + '-' + #size")
    @GetMapping(path = "/filterByPriceAsc")
    public ResponseEntity<Page<EbookResponse>> FilterEbookByPriceCheaper(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @AuthenticationPrincipal UserEntity user
    ) {
        return ResponseEntity.ok(customerService.FilterEbookByPriceCheaper(page, size));
    }

    @Cacheable(value = "bookDsc")
    @GetMapping(path = "/filterByPriceDsc")
    public ResponseEntity<Page<EbookResponse>> FilterEbookByPriceExpensive(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @AuthenticationPrincipal UserEntity user
    ) {
        return ResponseEntity.ok(customerService.FilterEbookByPriceExpensive(page, size));
    }

    @GetMapping(path = "/filterByPublisher")
    public ResponseEntity<Page<EbookResponse>> FilterEbookByPublisher(
            @RequestParam("publisher") String publisher,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @AuthenticationPrincipal UserEntity user
    ) {
        return ResponseEntity.ok(customerService.FilterEbookByPublisher(publisher, page, size));
    }

    @GetMapping(path = "/filterBook")
    public ResponseEntity<Page<EbookResponse>> getCurrentUser(
            @RequestParam("min") double min,
            @RequestParam("max") double max,
            @RequestParam("sort") String sort,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @AuthenticationPrincipal UserEntity user
    ) {
        return ResponseEntity.ok(customerService.filterBook(min, max, sort, page, size));
    }

    @GetMapping(path = "/filterBook2")
    public ResponseEntity<Page<EbookResponse>> filterBooks(
            @RequestParam("min") double min,
            @RequestParam("max") double max,
            @RequestParam("sort") String sort,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @AuthenticationPrincipal UserEntity user
    ) {
        return ResponseEntity.ok(customerService.filterBook(min, max, sort, page, size));
    }


    @GetMapping(path = "/get-message")
    public ResponseEntity<?> post() {
        return ResponseEntity.ok("Hello From GEt");
    }
}
