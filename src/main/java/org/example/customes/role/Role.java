package org.example.customes.role;

public enum Role {
    DEVELOPER("Разработчик"),
    USER("Пользователь"),
    HR("Менеджер по персоналу"),
    RECRUITER("Специалист по подбору"),
    DEVOPS("Инженер по инфраструктре"),
    TEAMLEAD("Руководитель команды");

    private final String description;

    Role(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }

    public static Role fromString(String value) {
        if (value == null) {
            throw new RuntimeException("Роль пуста");
        }

        return Role.valueOf(value.toUpperCase());
    }

}
