package com.hasanalmunawr.Ebook_Store.controller;

import com.hasanalmunawr.Ebook_Store.dto.EbookRequest;
import com.hasanalmunawr.Ebook_Store.service.EbookService;
import com.hasanalmunawr.Ebook_Store.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ebook")
@RequiredArgsConstructor
public class EbookController {

    private final EbookService ebookService;

    @PostMapping("/ebookInformation")
    public void ebookInformation(
            @RequestBody EbookRequest request,
            Authentication currentUser
            ) {
        UserEntity user = (UserEntity) currentUser.getPrincipal();

        ebookService.saveEbookInformation(request, user);
    }



}
