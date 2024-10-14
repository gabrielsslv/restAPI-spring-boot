package springapi.rest.controller;

public record CreateUserDto(String nome, String email, String endereco, Integer idade, String cpf, String cor) {

}
