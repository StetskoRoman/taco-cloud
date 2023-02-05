package sia.tacocloud.takoBase.data;

import sia.tacocloud.takoBase.Ingredient;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface IngredientRepository {

    Iterable<Ingredient> findAll(); //получить все ингредиенты в виде коллекции

    Optional<Ingredient> findById(String id); //получить 1 ингредиент по идентификатору

    Ingredient save(Ingredient ingredient);

}
