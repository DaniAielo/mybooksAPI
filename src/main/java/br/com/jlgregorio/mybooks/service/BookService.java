package br.com.jlgregorio.mybooks.service;

import br.com.jlgregorio.mybooks.dto.BookDTO;
import br.com.jlgregorio.mybooks.exception.ResourceNotFoundException;
import br.com.jlgregorio.mybooks.mapper.CustomModelMapper;
import br.com.jlgregorio.mybooks.model.BookModel;
import br.com.jlgregorio.mybooks.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public BookDTO findById(int id){
        BookModel model = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não Encontrado!"));
        return CustomModelMapper.parseObject(model, BookDTO.class);
    }

    public Page<BookDTO> findAll(Pageable pageable){
        var page = repository.findAll(pageable);
        //using the map to iterate the objects and execute a method over them
        return page.map( book -> CustomModelMapper.parseObject(book, BookDTO.class));
    }

    public BookDTO create(BookDTO dto){
        BookModel model = CustomModelMapper.parseObject(dto, BookModel.class);
        return CustomModelMapper.parseObject(repository.save(model), BookDTO.class);
    }

    public BookDTO update(BookDTO dto){
        var bookFound = repository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("Não Encontrado!"));
        var updated = CustomModelMapper.parseObject(dto, BookModel.class);
        return CustomModelMapper.parseObject(repository.save(updated), BookDTO.class);
    }

    public void delete(int id){
        var bookFound = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não Encontrado!"));
        repository.delete(bookFound);
    }

    public Page<BookDTO> findByAuthor(Pageable pageable, String author){
        var page = repository.findByAuthorNameStartsWithIgnoreCase(pageable, author);
        return page.map(book -> CustomModelMapper.parseObject(book, BookDTO.class));
    }

    public Page<BookDTO> findByTitle(Pageable pageable, String title){
        var page = repository.findByTitleContainsIgnoreCase(pageable, title);
        return page.map(book -> CustomModelMapper.parseObject(book, BookDTO.class));
    }

}
