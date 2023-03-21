package com.jeronima.helpdesk.usuario.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "usuario")
@Table(name = "usuario")
public class Usuario implements UserDetails {

    public Usuario(String id, String firstName, String lastName, String email, String password, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Usuario() {}

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List lista = new ArrayList();
        lista.add(new SimpleGrantedAuthority(role.name()));
        return lista;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getId() {
        return id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public static UsuarioBuilder builder() {
        return new UsuarioBuilder();
    }

    public static class UsuarioBuilder {
        private String id;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private Role role;

        UsuarioBuilder() {
        }

        public UsuarioBuilder id(final String id) {
            this.id = id;
            return this;
        }

        public UsuarioBuilder firstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UsuarioBuilder lastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UsuarioBuilder email(final String email) {
            this.email = email;
            return this;
        }

        public UsuarioBuilder password(final String password) {
            this.password = password;
            return this;
        }

        public UsuarioBuilder role(final Role role) {
            this.role = role;
            return this;
        }

        public Usuario build() {
            return new Usuario(this.id, this.firstName, this.lastName, this.email, this.password, this.role);
        }

        public String toString() {
            return "Usuario.UsuarioBuilder(id=" + this.id + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", email=" + this.email + ", password=" + this.password + ", role=" + this.role + ")";
        }
    }
}
