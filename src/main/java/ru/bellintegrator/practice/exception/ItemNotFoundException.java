package ru.bellintegrator.practice.exception;

/**
 * Ошибка, если данные не найдены
 */
public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(String message) {
        super(message);
    }
}
