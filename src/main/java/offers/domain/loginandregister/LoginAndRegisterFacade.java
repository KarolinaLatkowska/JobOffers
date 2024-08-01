package offers.domain.loginandregister;

import lombok.AllArgsConstructor;
import offers.domain.loginandregister.dto.RegisterUserDto;
import offers.domain.loginandregister.dto.RegistrationResultDto;
import offers.domain.loginandregister.dto.UserDto;

@AllArgsConstructor
public class LoginAndRegisterFacade {

    public static final String USER_NOT_FOUND = "User not found";

    private final LoginRepository loginRepository;

    public UserDto findByUsername(String username) {
        return loginRepository.findByUsername(username)
                .map(user -> new UserDto(user.id(), user.username(), user.password()))
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

    }

    public RegistrationResultDto register(RegisterUserDto registerUserDto) {
        final User user = User.builder()
                .username(registerUserDto.username())
                .password(registerUserDto.password())
                .build();
        User savedUser = loginRepository.save(user);
        return new RegistrationResultDto(savedUser.id(), true, savedUser.username());



    }
}
