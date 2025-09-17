package com.interview.ExpenseManagerAPI.security;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "User_Tabel")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(unique = true)
    private String username;

    private String password;

//    private List<String> roles;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
//        roles = List.of("USER" , "ADMIN");
//        return roles.stream()
//                .map(role -> new SimpleGrantedAuthority(role)) // required by Spring
//                .collect(Collectors.toList());
    }

}
