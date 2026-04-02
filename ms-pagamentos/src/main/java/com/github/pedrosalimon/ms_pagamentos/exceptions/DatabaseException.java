package com.github.pedrosalimon.ms_pagamentos.exceptions;

public class DatabaseException extends RuntimeException{

    public DatabaseException(String message) {
        super(message);
    }
}
