package ru.bellintegrator.practice.aspect;

/**
 * Ответ, возвращаемый при запросе
 */
public class ResponseToUser {

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
