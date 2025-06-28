package com.workintech.twitterapi.service;

import com.workintech.twitterapi.entity.User;
import com.workintech.twitterapi.exception.UserNotFoundException;
import com.workintech.twitterapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("test_user");
        user.setEmail("test@test.com");
    }

    @Test
    @DisplayName("Var olan bir kullanıcı adı ile arama yapıldığında kullanıcıyı döndürmeli")
    void findByUsername_whenUserExists_shouldReturnOptionalOfUser() {
        // ARRANGE
        // Kural: userRepository'nin findByUsername metodu "test_user" ile çağrıldığında,
        // içi dolu bir Optional<User> döndür.
        when(userRepository.findByUsername("test_user")).thenReturn(Optional.of(user));

        // ACT
        Optional<User> foundUserOptional = userService.findByUsername("test_user");

        // ASSERT
        // Dönen Optional'ın boş olmadığını (isPresent) ve içindeki kullanıcının
        // beklediğimiz kullanıcı olduğunu (assertEquals) doğrula.
        assertTrue(foundUserOptional.isPresent());
        assertEquals("test_user", foundUserOptional.get().getUsername());
    }

    @Test
    @DisplayName("Var olmayan bir kullanıcı adı ile arama yapıldığında boş Optional döndürmeli")
    void findByUsername_whenUserDoesNotExist_shouldReturnEmptyOptional() {
        // ARRANGE
        // Kural: userRepository'nin findByUsername metodu "non_existing_user" ile çağrıldığında,
        // içi boş bir Optional döndür.
        when(userRepository.findByUsername("non_existing_user")).thenReturn(Optional.empty());

        // ACT
        Optional<User> foundUserOptional = userService.findByUsername("non_existing_user");

        // ASSERT
        // Dönen Optional'ın boş olduğunu (isEmpty) doğrula.
        assertTrue(foundUserOptional.isEmpty());
    }

    @Test
    @DisplayName("Var olan bir ID ile kullanıcı arandığında kullanıcıyı döndürmeli")
    void getById_whenUserExists_shouldReturnUser() {
        // ARRANGE
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // ACT
        User foundUser = userService.getById(1L);

        // ASSERT
        // Dönen kullanıcının null olmadığını ve ID'sinin beklediğimiz ID olduğunu doğrula.
        assertNotNull(foundUser);
        assertEquals(1L, foundUser.getId());
    }

    @Test
    @DisplayName("Var olmayan bir ID ile kullanıcı arandığında UserNotFoundException fırlatmalı")
    void getById_whenUserDoesNotExist_shouldThrowUserNotFoundException() {
        // ARRANGE
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        // ACT & ASSERT
        // ID'si 99 olan bir kullanıcı arandığında,
        // UserNotFoundException TİPİNDE bir hata fırlatılacağını BEKLE.
        assertThrows(UserNotFoundException.class, () -> {
            userService.getById(99L);
        });
    }
}
