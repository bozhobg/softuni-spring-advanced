package bg.softuni.mobiLeLeLe.model.dto;

public record UserRegistrationDto(
        String firstName,
        String lastName,
        String username,
        String password,
        String userRole
        ) {
}
