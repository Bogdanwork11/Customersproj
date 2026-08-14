package org.example.customes.dto;

public record LoginRequest( //record напоминает о том что это компактный способ обьявить класс, предназначенный только для хранения данных то есть только getterow
    String login,
    String password
)
{}
