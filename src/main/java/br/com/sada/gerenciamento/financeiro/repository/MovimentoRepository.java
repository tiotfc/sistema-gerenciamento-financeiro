package br.com.sada.gerenciamento.financeiro.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sada.gerenciamento.financeiro.model.Categoria;
import br.com.sada.gerenciamento.financeiro.model.Movimento;

@Repository
public interface MovimentoRepository extends JpaRepository<Movimento, Integer> {

	List<Movimento> findByDataInclusaoBetween(LocalDate firstDayOfMonth, LocalDate lastDayOfMonth);

	List<Movimento> findByDataInclusao(LocalDate currentDate);
	
	List<Movimento> findByDataInclusaoBetweenAndCategoria(LocalDate firstDayOfMonth, LocalDate lastDayOfMonth, Categoria categoria);

}
