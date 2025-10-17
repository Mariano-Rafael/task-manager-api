package io.task_manager.task_manager_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateDTO(
    @NotBlank(message = "O nome não pode estar em branco.")
    String name,

    @NotBlank(message = "O e-mail não pode estar em branco.")
    @Email(message = "E-mail inválido.")
    String email,

    @NotBlank(message = "A senha não pode estar em branco.")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres.")
    String password

) {}


