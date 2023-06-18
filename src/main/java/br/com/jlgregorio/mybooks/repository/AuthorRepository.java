package br.com.jlgregorio.mybooks.repository;

import br.com.jlgregorio.mybooks.model.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorModel, Integer> {


}
