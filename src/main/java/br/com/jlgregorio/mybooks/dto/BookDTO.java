package br.com.jlgregorio.mybooks.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Relation(itemRelation = "book", collectionRelation = "books")
public class BookDTO extends RepresentationModel<BookDTO> {

    private int id;

    private String title;

    private AuthorDTO author;

    private CategoryDTO category;


}
