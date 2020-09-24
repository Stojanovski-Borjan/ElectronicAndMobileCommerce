package mk.ukim.finki.rentscoot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.security.AuthProvider;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "client",fetch = FetchType.EAGER)
    private List<Reservation> reservation;

    @NotNull
    private String name;

    @Email
    @NotNull
    private String email;

    /*@NotNull
    private Boolean emailVerified = false;*/

    @JsonIgnore
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role = Role.CLIENT;

    /*@NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerID;*/

    public User(String name,String email,String password,Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
