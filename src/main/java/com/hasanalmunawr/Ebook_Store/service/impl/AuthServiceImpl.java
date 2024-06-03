package com.hasanalmunawr.Ebook_Store.service.impl;

import com.hasanalmunawr.Ebook_Store.code.CodeEntity;
import com.hasanalmunawr.Ebook_Store.code.CodeRepository;
import com.hasanalmunawr.Ebook_Store.dto.LoginRequest;
import com.hasanalmunawr.Ebook_Store.dto.RegisterRequest;
import com.hasanalmunawr.Ebook_Store.dto.response.LoginResponse;
import com.hasanalmunawr.Ebook_Store.exception.CodeInvalidException;
import com.hasanalmunawr.Ebook_Store.exception.UserAlreadyExistException;
import com.hasanalmunawr.Ebook_Store.security.JwtService;
import com.hasanalmunawr.Ebook_Store.service.AuthService;
import com.hasanalmunawr.Ebook_Store.token.TokenEntity;
import com.hasanalmunawr.Ebook_Store.token.TokenRepository;
import com.hasanalmunawr.Ebook_Store.token.TokenType;
import com.hasanalmunawr.Ebook_Store.user.Role;
import com.hasanalmunawr.Ebook_Store.user.UserEntity;
import com.hasanalmunawr.Ebook_Store.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.security.auth.login.AccountNotFoundException;
import java.security.SecureRandom;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final CodeRepository codeRepository;
//    private final EmailService emailService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    @Override
    public Integer register(RegisterRequest request) {
        log.info("Register request: {}", request);
        try {
            var userEmail = userRepository.findByEmail(request.getEmail());
            if (userEmail.isPresent()) {
                throw new UserAlreadyExistException("User With "+request.getEmail()+" Already Exist");
            }

            UserEntity user = UserEntity.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .role(convertStrToRole(request.getRole()))
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .phone(request.getPhone())
                    .enabled(false)
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .build();

            UserEntity savedUser = userRepository.save(user);
            sendValidationEmail(savedUser);
//
//            return RegisterResponse.builder()
//                    .accountInfo(convertUserToACI(user))
//                    .build();

            return generateAndSaveActivationCode(savedUser);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @Override
    public LoginResponse login(LoginRequest request) {
        log.info("Login request: {}", request.email());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );
            var userLogin = userRepository.findByEmail(request.email())
                    .orElseThrow(AccountNotFoundException::new);

            String generatedToken = jwtService.generateToken(userLogin);
            long accessExpiration = jwtService.getJwtExpiration();

            savedUserToken(generatedToken, userLogin);
            return LoginResponse.builder()
                    .accessTokenExpiry((int) accessExpiration)
                    .accessToken(generatedToken)
                    .build();
        } catch (Exception e) {
            throw new UsernameNotFoundException("Email Not Found");
        }
    }


    @Transactional
    @Override
    public void activateAccount(Integer code) {
        CodeEntity codeEntity = codeRepository.findByTokenCode(code)
                .orElseThrow(CodeInvalidException::new);
        codeEntity.setValidatedAt(now());
log.info("AuthService: Activate account with code: {}", codeEntity);
        if (now().isAfter(codeEntity.getExpiresAt())) {
            throw new CodeInvalidException("Activation token has expired");
        }

        var userEntity = userRepository.findById(codeEntity.getUser().getId())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        log.info("AuthService: Code has Activated : {}", code);
        userEntity.setEnabled(true);
        userRepository.save(userEntity);
    }

    private Role convertStrToRole(String role) {
        if (role.equalsIgnoreCase("admin")) {
            return Role.ADMIN;
        } else if (role.equalsIgnoreCase("user")) {
            return Role.USER;
        } else {
            throw new IllegalArgumentException("Invalid role");
        }
    }

    private void savedUserToken(String jwtToken, UserEntity userLogin) {
        TokenEntity token = TokenEntity.builder()
                .token(jwtToken)
                .user(userLogin)
                .tokenType(TokenType.BEARER)
                .isRevoked(false)
                .isExpired(false)
                .build();

        tokenRepository.save(token);
    }

    private void sendValidationEmail(UserEntity user)  {
        var newToken = generateAndSaveActivationCode(user);

//        emailService.sendEmailAcctivateAccount(
//                user.getEmail(),
//                user.getFullName(),
//                EmailTemplateName.ACTIVATE_ACCOUNT,
//                newToken,
//                ACTIVATION_ACCOUNT
//        );
    }

    private Integer generateAndSaveActivationCode(UserEntity user) {
        Integer generatedCode = generateActivationCode();

        CodeEntity token = CodeEntity.builder()
                .tokenCode(generatedCode)
                .expiresAt(now().plusMinutes(10))
                .user(user)
                .build();
        codeRepository.save(token);

        return generatedCode;
    }

    private Integer generateActivationCode() {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < 6; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }

        return Integer.parseInt(codeBuilder.toString());
    }

}
