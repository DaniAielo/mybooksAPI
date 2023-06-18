package br.com.jlgregorio.mybooks.repository;

import br.com.jlgregorio.mybooks.model.CategoryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Integer> {

    public Page<CategoryModel> findAll(Pageable pageable);

    //..query methods
    public Page<CategoryModel> findByNameStartsWithIgnoreCaseOrderByName(Pageable pageable, String name);



}
