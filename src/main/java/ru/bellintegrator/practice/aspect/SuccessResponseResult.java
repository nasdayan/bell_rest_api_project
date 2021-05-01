package ru.bellintegrator.practice.aspect;

/**
 * Объект, возвращаемый при успешном выполнении операции сохранения или редактирования
 */
public class SuccessResponseResult {

    private final String result = "success";

    public String getResult() {
        return result;
    }
}
