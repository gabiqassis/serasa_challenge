package dev.gabiqassis.serasa.domain.util;

import dev.gabiqassis.serasa.domain.request.UserCreaterRequest;
import dev.gabiqassis.serasa.domain.request.UserUpdateRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Validation {
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String PHONE_PATTERN = "^\\(?\\d{2}\\)?\\s?\\d{4,5}-\\d{4}$";
    private static final Logger logger = LoggerFactory.getLogger(Validation.class);

    public static <T> T validationField(T user) {
        if (user instanceof UserCreaterRequest) {
            logger.info("Iniciando a validação para criação do usuário");

            UserCreaterRequest userCreaterRequest = (UserCreaterRequest) user;
            validateCpf(userCreaterRequest.getCpf());
            validateEmail(userCreaterRequest.getEmail());
            validatePhoneNumber(userCreaterRequest.getPhoneNumber());
        } else if (user instanceof UserUpdateRequest) {
            logger.info("Iniciando a validação para atualização do usuário");

            UserUpdateRequest userUpdateRequest = (UserUpdateRequest) user;
            validateCpf(userUpdateRequest.getCpf());
            validateEmail(userUpdateRequest.getEmail());
            validatePhoneNumber(userUpdateRequest.getPhoneNumber());
        } else {
            throw new IllegalArgumentException("Tipo de requisição inválido para validação.");
        }

        logger.info("Validação concluída com sucesso.");
        return user;
    }
    private static String validateCpf(String cpf) {
        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}") || !validateDigitosCheckers(cpf)) {
            logger.error("CPF inválido");
            throw new IllegalArgumentException("CPF inválido.");
        }

        return cpf;
    }

    private static boolean validateDigitosCheckers(String cpf) {
        int firstVerifier = calculateDigital(cpf, 9);
        int secondVerifier = calculateDigital(cpf, 10);
        return cpf.charAt(9) == (char) (firstVerifier + '0') &&
                cpf.charAt(10) == (char) (secondVerifier + '0');
    }

    private static int calculateDigital(String cpf, int length) {
        int sum = 0;
        int weight = length + 1;

        for (int i = 0; i < length; i++) {
            sum += (cpf.charAt(i) - '0') * weight--;
        }

        int resto = 11 - (sum % 11);
        return (resto > 9) ? 0 : resto;
    }

    private static void validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);

        if (!pattern.matcher(email).matches()) {
            logger.error("Email inválido");
            throw new IllegalArgumentException("Email inválido.");
        }
    }

    private static void validatePhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile(PHONE_PATTERN);

        if (!pattern.matcher(phoneNumber).matches()) {
            logger.error("Telefone inválido");
            throw new IllegalArgumentException("Telefone inválido.");
        }
    }

}