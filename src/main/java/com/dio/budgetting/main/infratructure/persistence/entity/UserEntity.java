package com.dio.budgetting.main.infratructure.persistence.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.dio.budgetting.main.domain.User;
import com.dio.budgetting.main.domain.id.UserId;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    private UUID id;

    @Column(unique = true, nullable = false, length = 100)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    public static UserEntity from(User user) {
        return new UserEntity(
        		user.getId().uuid(),
        		user.getUsername(),
        		user.getPassword(),
        		user.getRoles());
    }
    
    public User toDomain() {
        return new User(
        		new UserId(this.id),
                this.username,
                this.password,
                this.roles);
    }
 
}