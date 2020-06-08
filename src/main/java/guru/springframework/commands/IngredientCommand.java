package guru.springframework.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by jt on 6/21/17.
 */
@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
    private Long id;
    private String description;
    private BigDecimal amount;
    //private UnitOfMeasureCommand unitOfMeasure; böyle iken çalışmadı çünkü
    //ingredient.uom vardı recipeform.html'de recipeform.htmldeki rcipe5 hem recipe hem de recipe command objeleri tarafından
    //maplendiği için ikisinin de attribute ları aynı olmalı
    private UnitOfMeasureCommand uom;

}
