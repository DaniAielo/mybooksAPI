package br.com.jlgregorio.mybooks.service;

import br.com.jlgregorio.mybooks.dto.CategoryDTO;
import br.com.jlgregorio.mybooks.exception.ResourceNotFoundException;
import br.com.jlgregorio.mybooks.mapper.CustomModelMapper;
import br.com.jlgregorio.mybooks.model.CategoryModel;
import br.com.jlgregorio.mybooks.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public CategoryDTO create(CategoryDTO dto){
        CategoryModel model = CustomModelMapper.parseObject(dto, CategoryModel.class);
        CategoryModel newModel = repository.save(model);
        return CustomModelMapper.parseObject(newModel, CategoryDTO.class);
        //return CustomModelMapper.parseObject(repository.save(model), CategoryDTO.class);
    }

    public CategoryDTO findById(int id){
       CategoryModel model = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não encontrado!"));
       return CustomModelMapper.parseObject(model, CategoryDTO.class);
    }

    public Page<CategoryDTO> findAll(Pageable pageable){
        var page = repository.findAll(pageable);
        return page.map(category -> CustomModelMapper.parseObject(category, CategoryDTO.class));
    }

    public Page<CategoryDTO> findByName(Pageable pageable, String name){
        var page = repository.findByNameStartsWithIgnoreCaseOrderByName(pageable, name);
        return page.map(category -> CustomModelMapper.parseObject(category, CategoryDTO.class));
    }

    public CategoryDTO update(CategoryDTO dto){
        var categoryFound = repository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Não encontrado!"));
        categoryFound = CustomModelMapper.parseObject(dto, CategoryModel.class);
        return CustomModelMapper.parseObject(repository.save(categoryFound), CategoryDTO.class);
    }

    public void delete(int id){
        var categoryFound = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não encontrado!"));
        repository.delete(categoryFound);
    }

}
