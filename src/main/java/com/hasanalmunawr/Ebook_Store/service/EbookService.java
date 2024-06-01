package com.hasanalmunawr.Ebook_Store.service;

import com.hasanalmunawr.Ebook_Store.dto.EbookRequest;
import com.hasanalmunawr.Ebook_Store.user.UserEntity;
import org.springframework.security.core.Authentication;

public interface EbookService {

    void saveEbookInformation(EbookRequest request, UserEntity currentUser);
}
