package com.example.mlbb.enums;

public enum MatchStatus {
    SCHEDULED,        // создан, ждёт соперника
    WAITING_REPORTS,    // команда приняла
    COMPLETED,   // обе стороны подтвердили
    DISPUTED,   // сыгран
    CANCELED     // отменён
}
