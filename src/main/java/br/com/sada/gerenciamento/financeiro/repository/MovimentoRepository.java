package br.com.sada.gerenciamento.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sada.gerenciamento.financeiro.model.Movimento;

@Repository
public interface MovimentoRepository extends JpaRepository<Movimento, Integer> {

}
