package com.example.errorhandling.demo.application.service;

import com.example.errorhandling.demo.application.service.error.GeneralError;
import com.example.errorhandling.demo.application.service.error.ReportError.ReportForUserNotFoundError;
import com.example.errorhandling.demo.application.service.error.ServiceError.DatabaseError;
import com.example.errorhandling.demo.application.service.exception.DatabaseAccessException;
import com.example.errorhandling.demo.application.service.exception.UserNotFoundInDbException;
import com.example.errorhandling.demo.domain.User;
import com.example.errorhandling.demo.infrastructure.UserRepository;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public Either<GeneralError, User> getUserV2(String userId) {
    return Try.of(() -> userRepository.getUser(userId))
      .toEither()
      .map(Option::ofOptional)
      .<GeneralError>mapLeft(DatabaseError::new)
      .flatMap(optionalUser -> optionalUser.toEither(new ReportForUserNotFoundError(userId)));
  }

  public User getUserV1(String userId) {
    Optional<User> optionalUser;
    try {
      optionalUser = userRepository.getUser(userId);
    } catch (Exception e) {
      throw new DatabaseAccessException(e);
    }
    return optionalUser.orElseThrow(() -> new UserNotFoundInDbException(userId));
  }



}
