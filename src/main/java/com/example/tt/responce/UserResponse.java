package com.example.tt.responce;

import com.example.tt.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse{
    private Long id;
    private String email;
    private String name;
    private String lastname;
    private Role role;
}
