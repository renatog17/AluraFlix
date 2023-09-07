package br.com.renato.aluraflix;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordExample {
    public static void main(String[] args) {
        // Crie um objeto BCryptPasswordEncoder com um nível de força (strength) adequado
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); // Você pode ajustar a força conforme necessário

        // Senha que você deseja codificar
        String senha = "123";

        // Codifique a senha
        String senhaCodificada = encoder.encode(senha);

        // Imprima a senha codificada
        System.out.println("Senha codificada: " + senhaCodificada);

        // Verifique se uma senha inserida posteriormente corresponde à senha codificada
        boolean senhaCorreta = encoder.matches("123", senhaCodificada);
        System.out.println("Senha correta: " + senhaCorreta);
    }
}
