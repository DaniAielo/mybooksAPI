package br.com.jlgregorio.mybooks.repository;

import br.com.jlgregorio.mybooks.model.BookModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Integer> {

    public Page<BookModel> findAll(Pageable pageable);

    public Page<BookModel> findByAuthorNameStartsWithIgnoreCase(Pageable pageable, String author);

    public Page<BookModel> findByTitleContainsIgnoreCase(Pageable pageable, String title);

}
