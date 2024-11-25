package org.example.wanted.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.wanted.util.enumeration.ErrorCode;

@Getter
@AllArgsConstructor
public class EntityNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;
}
