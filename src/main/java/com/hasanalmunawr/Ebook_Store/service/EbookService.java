package com.hasanalmunawr.Ebook_Store.service;

import com.hasanalmunawr.Ebook_Store.dto.EbookRequest;
import com.hasanalmunawr.Ebook_Store.dto.UpdateEbookRequest;
import com.hasanalmunawr.Ebook_Store.dto.response.EbookResponse;
import com.hasanalmunawr.Ebook_Store.user.UserEntity;

public interface EbookService {

    void saveEbookInformation(EbookRequest request, UserEntity currentUser);

    EbookResponse getEbookInformation(String isbn);

    EbookResponse updateEbookInformation(UpdateEbookRequest request, String isbn, UserEntity currentUser);

    void deleteEbook(String isbn);


}
