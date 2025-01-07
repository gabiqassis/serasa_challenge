package dev.gabiqassis.serasa.domain.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;

@Validated
public class UserCreaterRequest{
        @NotBlank(message = "Nome é obrigatório")
        private String name;

        @NotBlank(message = "CPF é obrigatório")
        private String cpf;

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Esse email não é válido")
        private String email;

        @NotBlank(message = "Telefone é obrigatório")
        private String phoneNumber;

        public UserCreaterRequest(String name, String cpf, String email, String phoneNumber) {
                this.name = name;
                this.cpf = cpf;
                this.email = email;
                this.phoneNumber = phoneNumber;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getCpf() {
                return cpf;
        }

        public void setCpf(String cpf) {
                this.cpf = cpf;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPhoneNumber() {
                return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
        }
}
