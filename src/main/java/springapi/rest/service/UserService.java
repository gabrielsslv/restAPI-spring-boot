package springapi.rest.service;

import org.springframework.stereotype.Service;
import springapi.rest.controller.CreateUserDto;
import springapi.rest.controller.UpdateUserDto;
import springapi.rest.entities.User;
import springapi.rest.reposiory.UserRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDto createUserDto){
        var entity = new User(UUID.randomUUID(),
                createUserDto.nome(),
                createUserDto.email(),
                createUserDto.endereco(),
                createUserDto.idade(),
                createUserDto.cpf(),
                createUserDto.cor(),
                Instant.now(),
                null
        );

        var userSaved = userRepository.save(entity);

        return userSaved.getUserId();
    }

    public Optional<User> getUserById(String userId){
        return userRepository.findById(UUID.fromString(userId));
    }

    public List<User> listUsers(){
        return userRepository.findAll();
    }

    public void updateUserById(String userId, UpdateUserDto updateUserDto){
        var id = UUID.fromString(userId);
        var userEntity = userRepository.findById(id);

        if (userEntity.isPresent()){
            var user = userEntity.get();

            if (updateUserDto.nome() != null){
                user.setNome(updateUserDto.nome());
            }

            if (updateUserDto.email() != null){
                user.setEmail(updateUserDto.email());
            }

            if (updateUserDto.endereco() != null){
                user.setEndereco(updateUserDto.endereco());
            }

            if (updateUserDto.cor() != null){
                user.setCor(updateUserDto.cor());
            }

            userRepository.save(user);
        }
    }

    public void deleteById(String userId){
        var id = UUID.fromString(userId);
        var userExists = userRepository.existsById(id);

        if (userExists) {
            userRepository.deleteById(id);
        }
    }
}
