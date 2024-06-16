package com.hasanalmunawr.Ebook_Store.configuration;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "EBOOK STORE API",
                version = "1.0.0",
                description = "RESTful API Ebook store",
                contact = @Contact(
                        url = "https://www.hasan.com",
                        email = "hasanalmunawar9@gmail.com")
        )
)
public class OpenAPI {
}
