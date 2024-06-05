package com.hasanalmunawr.Ebook_Store.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hasanalmunawr.Ebook_Store.book.EbookEntity;
import com.hasanalmunawr.Ebook_Store.code.CodeEntity;
import com.hasanalmunawr.Ebook_Store.token.TokenEntity;
import com.hasanalmunawr.Ebook_Store.transaction.TransactionEntity;
import com.hasanalmunawr.Ebook_Store.wishlist.WishListEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity extends Auditable implements UserDetails {

    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;
    private String password;

    private LocalDateTime lastLogin;
    private String phone;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "user")
    private WishListEntity wishList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<TokenEntity> tokens;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CodeEntity> codes;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<EbookEntity> ebooks;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TransactionEntity> transactions;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
