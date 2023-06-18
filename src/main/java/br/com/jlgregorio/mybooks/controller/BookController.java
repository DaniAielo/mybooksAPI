package br.com.jlgregorio.mybooks.controller;

import br.com.jlgregorio.mybooks.dto.BookDTO;
import br.com.jlgregorio.mybooks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("/api/book/v1")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping("/{id}")
    public BookDTO findById(@PathVariable("id") int id){
        BookDTO dto = service.findById(id);
        buildSelfLink(dto);
        return dto;
    }

    @GetMapping
    public ResponseEntity<PagedModel<BookDTO>>  findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            PagedResourcesAssembler<BookDTO> assembler
    ){
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "title"));
        Page<BookDTO> books = service.findAll(pageable);
        for(BookDTO book : books){
            buildSelfLink(book);
        }
        return new ResponseEntity(assembler.toModel(books), HttpStatus.OK);
    }

    @GetMapping("/find/author/")
    public ResponseEntity<PagedModel<BookDTO>> findByAuthor(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestParam(value = "direction", required = false, defaultValue = "desc") String direction,
            @RequestParam(value = "author", required = true) String author,
            PagedResourcesAssembler<BookDTO> assembler
    ){
        var sortDiretion = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDiretion, "title" ));
        Page<BookDTO> books = service.findByAuthor(pageable, author);
        for(BookDTO book : books){
            buildSelfLink(book);
        }
        return new ResponseEntity(assembler.toModel(books), HttpStatus.OK);
    }

    @GetMapping("/find/title/")
    public ResponseEntity<PagedModel<BookDTO>> findByTitle(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestParam(value = "direction", required = false, defaultValue = "desc") String direction,
            @RequestParam(value = "title", required = true) String title,
            PagedResourcesAssembler<BookDTO> assembler
    ){
        var sortDiretion = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDiretion, "title" ));
        Page<BookDTO> books = service.findByTitle(pageable, title);
        for(BookDTO book : books){
            buildSelfLink(book);
        }
        return new ResponseEntity(assembler.toModel(books), HttpStatus.OK);
    }

    @PostMapping
    public BookDTO create(@RequestBody BookDTO dto){
        return service.create(dto);
    }

    @PutMapping
    public BookDTO update(@RequestBody BookDTO dto){
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id){
        service.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    private void buildSelfLink(BookDTO dto){
        //..link to self
        dto.add(
                WebMvcLinkBuilder.linkTo(
                  WebMvcLinkBuilder.methodOn(
                          this.getClass()
                  ).findById(dto.getId())
                ).withSelfRel()
        );
        //..link to category
        dto.getCategory().add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(CategoryController.class)
                                .findById(dto.getCategory().getId())
                ).withSelfRel()
        );
        //..lint to author
        dto.getAuthor().add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(AuthorController.class)
                                .findById(dto.getAuthor().getId())
                ).withSelfRel()
        );
        //Think about these methods! Would not been better they are in DTO classes?
    }




}
