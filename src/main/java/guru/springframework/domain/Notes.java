package guru.springframework.domain;

import lombok.*;

import javax.persistence.*;
@Data
@Entity
@EqualsAndHashCode(exclude = {"recipe"})
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne //if we delete notes we dont want to delete recipe so there is no cascade here.
    private Recipe recipe;
    @Lob //with string it becomes clob
    private String recipeNotes;


}
