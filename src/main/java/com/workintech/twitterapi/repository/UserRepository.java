package com.workintech.twitterapi.repository;

import com.workintech.twitterapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}


//    /register endpointi için -----JpaRepository save(User user) hediye eder


//    /login    endpointi----- yukarıdaki metoduu kullanır
// optional var ya da yok döner içi boş optional kutusu
//.isPresent(), .orElseThrow() gibi metotları kullanarız
// bu durumu kontrol etmeye zorlar
// NullPointerException riskini ortadan kaldırır