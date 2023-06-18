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
@Relation(itemRelation = "author", collectionRelation = "authors")
public class AuthorDTO extends RepresentationModel<AuthorDTO> {

    private int id;
    private String name;

    private String gender;

}
