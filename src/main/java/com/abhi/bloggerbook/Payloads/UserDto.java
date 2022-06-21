package com.abhi.bloggerbook.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;

    @NonNull
    @NotEmpty
    private String name;

    @Email(message = "email address is not valid")
    private String email;

    @NonNull
    @Size(min=4,max=20,message="password must contain min 4-20 characters")
    private String password;

    private String about;
}
