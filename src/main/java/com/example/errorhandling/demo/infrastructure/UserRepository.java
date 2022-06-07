package com.example.errorhandling.demo.infrastructure;

import com.example.errorhandling.demo.domain.User;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.stereotype.Service;

@Service
public class UserRepository {

  public Optional<User> getUser(String userId) {
    return switch (ThreadLocalRandom.current().nextInt(0, 10)) {
      case 0 -> throw new RuntimeException("some db errror");
      case 1 -> Optional.empty();
      default -> Optional.of(new User(userId, "email"));
    };
  }

}
