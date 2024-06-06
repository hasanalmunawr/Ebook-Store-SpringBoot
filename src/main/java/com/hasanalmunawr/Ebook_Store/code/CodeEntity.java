package com.hasanalmunawr.Ebook_Store.code;

import com.hasanalmunawr.Ebook_Store.user.Auditable;
import com.hasanalmunawr.Ebook_Store.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "codes")
public class CodeEntity extends Auditable {

    @Column(unique = true)
    private Integer tokenCode;
    private LocalDateTime expiresAt;
    private LocalDateTime validatedAt;
    private String type;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
