package br.furb.suficiencia.domain.comanda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComandaRepository extends JpaRepository<ComandaEntity, Long> {
}
