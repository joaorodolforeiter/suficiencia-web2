package br.furb.suficiencia.config;

import br.furb.suficiencia.domain.comanda.ComandaEntity;
import br.furb.suficiencia.domain.comanda.ComandaRepository;
import br.furb.suficiencia.domain.produto.ProdutoEntity;
import br.furb.suficiencia.domain.user.UserEntity;
import br.furb.suficiencia.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ComandaRepository comandaRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {
            UserEntity user1 = new UserEntity();
            user1.setUsername("admin");
            user1.setPassword(passwordEncoder.encode("admin123"));
            user1.setEmail("admin@furb.br");
            userRepository.save(user1);

            UserEntity user2 = new UserEntity();
            user2.setUsername("user");
            user2.setPassword(passwordEncoder.encode("user123"));
            user2.setEmail("user@furb.br");
            userRepository.save(user2);
        }

        if (comandaRepository.count() == 0) {
            ProdutoEntity produto1 = new ProdutoEntity();
            produto1.setId(1L);
            produto1.setNome("X-Salada");
            produto1.setPreco(new BigDecimal("30.00"));

            ProdutoEntity produto2 = new ProdutoEntity();
            produto2.setId(2L);
            produto2.setNome("X-Bacon");
            produto2.setPreco(new BigDecimal("35.00"));

            ProdutoEntity produto3 = new ProdutoEntity();
            produto3.setId(3L);
            produto3.setNome("X-Galinha");
            produto3.setPreco(new BigDecimal("25.00"));

            ComandaEntity comanda1 = new ComandaEntity();
            comanda1.setIdUsuario(1L);
            comanda1.setNomeUsuario("joao");
            comanda1.setTelefoneUsuario("478888888");
            comanda1.setProdutos(Arrays.asList(produto1, produto2));

            ComandaEntity comanda2 = new ComandaEntity();
            comanda2.setIdUsuario(2L);
            comanda2.setNomeUsuario("Pedro");
            comanda2.setTelefoneUsuario("47999999");
            comanda2.setProdutos(Arrays.asList(produto3));

            comandaRepository.save(comanda1);
            comandaRepository.save(comanda2);
        }
    }
}
