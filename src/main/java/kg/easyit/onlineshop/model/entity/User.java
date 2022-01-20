package kg.easyit.onlineshop.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "tb_user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends AbstractPersistable<Long> implements UserDetails {

    @Column(name = "first_name", nullable = false)
    String firstName;

    @Column(name = "last_name", nullable = false)
    String lastName;

    @Column(name = "username", nullable = false, unique = true)
    String username;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name= "email", nullable = false, unique = true)
    String email;

    @Column(name = "phone_number", nullable = false)
    String phoneNumber;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Basket> basket;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Account> accounts;

    @Column(name = "is_active", nullable = false)
    Boolean isActive;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities()
                .stream().map(authority -> new SimpleGrantedAuthority(authority.name()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
