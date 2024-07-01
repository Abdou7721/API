package com.api.ticket.Apiticket;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasseHash {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123456"));  // Encode your passwords here
    }
}
