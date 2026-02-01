package com.guciowons.yummify.auth.domain.model.value;

public record PersonalData(String firstName, String lastName) {
    public static PersonalData of(String firstName, String lastName) {
        return new PersonalData(firstName, lastName);
    }
}
