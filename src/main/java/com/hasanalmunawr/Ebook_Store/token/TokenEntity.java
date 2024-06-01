package com.hasanalmunawr.Ebook_Store.token;


import com.hasanalmunawr.Ebook_Store.user.Auditable;
import com.hasanalmunawr.Ebook_Store.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tokens")
public class TokenEntity extends Auditable {

    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    public boolean isRevoked;
    public boolean isExpired;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public UserEntity user;
}
