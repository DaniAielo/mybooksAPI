package br.com.jlgregorio.mybooks.dto;

import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "CategoryDTO", description = "Category Representation")
@Relation(itemRelation = "category", collectionRelation = "categories")
public class CategoryDTO extends RepresentationModel<CategoryDTO> {

    private int id;
    private String name;

}
