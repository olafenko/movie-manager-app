package com.olafenko.moviemanagerapp.User.dtos;

public record RegisterRequest(String email, String password, String firstname, String lastname) {
}
