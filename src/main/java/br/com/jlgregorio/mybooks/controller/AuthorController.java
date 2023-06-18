package br.com.jlgregorio.mybooks.controller;

import br.com.jlgregorio.mybooks.dto.AuthorDTO;
import br.com.jlgregorio.mybooks.dto.BookDTO;
import br.com.jlgregorio.mybooks.service.AuthorService;
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

import java.util.List;

@RestController
@RequestMapping("/api/author/v1")
public class AuthorController {

    @Autowired
    private AuthorService service;

    @PostMapping
    public AuthorDTO create(@RequestBody AuthorDTO dto){
        return service.create(dto);
    }

    @GetMapping("/{id}")
    public AuthorDTO findById(@PathVariable("id") int id){
        return service.findById(id);
    }

    @GetMapping
    public ResponseEntity<PagedModel<AuthorDTO>> findAll(
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "10") int size,
        @RequestParam(value = "direction", defaultValue = "asc") String direction,
        PagedResourcesAssembler<AuthorDTO> assembler
    ){
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));
        Page<AuthorDTO> authors = service.findAll(pageable);
        for(AuthorDTO author : authors){
            buildSelfLink(author);
        }
        return new ResponseEntity(assembler.toModel(authors), HttpStatus.OK);
    }


    @PutMapping
    public AuthorDTO update(@RequestBody AuthorDTO dto){
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id){
        service.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    private void buildSelfLink(AuthorDTO dto){
        //..link to self
        dto.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(
                                this.getClass()
                        ).findById(dto.getId())
                ).withSelfRel()
        );
    }


}
