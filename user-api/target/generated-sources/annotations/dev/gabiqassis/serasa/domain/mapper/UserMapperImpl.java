package dev.gabiqassis.serasa.domain.mapper;

import dev.gabiqassis.serasa.domain.entity.User;
import dev.gabiqassis.serasa.domain.request.UserCreaterRequest;
import dev.gabiqassis.serasa.domain.request.UserUpdateRequest;
import dev.gabiqassis.serasa.domain.response.UserResponse;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-20T18:57:46-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User map(UserCreaterRequest userCreateRequest) {
        if ( userCreateRequest == null ) {
            return null;
        }

        User user = new User();

        user.setName( userCreateRequest.name() );
        user.setCpf( userCreateRequest.cpf() );
        user.setEmail( userCreateRequest.email() );
        user.setPhoneNumber( userCreateRequest.phoneNumber() );

        return user;
    }

    @Override
    public UserResponse map(User user) {
        if ( user == null ) {
            return null;
        }

        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;
        Long id = null;
        String name = null;
        String cpf = null;
        String email = null;
        String phoneNumber = null;

        createdAt = user.getCreatedAt();
        updatedAt = user.getUpdatedAt();
        id = user.getId();
        name = user.getName();
        cpf = user.getCpf();
        email = user.getEmail();
        phoneNumber = user.getPhoneNumber();

        UserResponse userResponse = new UserResponse( createdAt, updatedAt, id, name, cpf, email, phoneNumber );

        return userResponse;
    }

    @Override
    public void map(UserUpdateRequest userUpdateRequest, User user) {
        if ( userUpdateRequest == null ) {
            return;
        }

        user.setName( userUpdateRequest.getName() );
        user.setCpf( userUpdateRequest.getCpf() );
        user.setEmail( userUpdateRequest.getEmail() );
        user.setPhoneNumber( userUpdateRequest.getPhoneNumber() );
    }
}
