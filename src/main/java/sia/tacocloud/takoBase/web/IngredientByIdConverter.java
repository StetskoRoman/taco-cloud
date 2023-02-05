package sia.tacocloud.takoBase.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import sia.tacocloud.takoBase.Ingredient;

import sia.tacocloud.takoBase.Ingredient.Type;
import sia.tacocloud.takoBase.data.IngredientRepository;

import java.util.HashMap;
import java.util.Map;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private IngredientRepository ingredientRepo; //везде так интерфейс инициализируем и используем БД, таблица еще пустая
    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    //    private Map<String, Ingredient> ingredientMap = new HashMap<>(); //по id будем ингредиент опознавать
//    @Autowired
//    public IngredientByIdConverter() { //потом надо из БД всё вытаскивать по этому методу
        //убираем статические ингредиенты
//        ingredientMap.put("FLTO",
//        new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
//        ingredientMap.put("COTO",
//                new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
//        ingredientMap.put("GRBF",
//                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
//        ingredientMap.put("CARN",
//                new Ingredient("CARN", "Carnitas", Type.PROTEIN));
//        ingredientMap.put("TMTO",
//                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
//        ingredientMap.put("LETC",
//                new Ingredient("LETC", "Lettuce", Type.VEGGIES));
//        ingredientMap.put("CHED",
//                new Ingredient("CHED", "Cheddar", Type.CHEESE));
//        ingredientMap.put("JACK",
//                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
//        ingredientMap.put("SLSA",
//                new Ingredient("SLSA", "Salsa", Type.SAUCE));
//        ingredientMap.put("SRCR",
//                new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

    @Override
    public Ingredient convert(String source) {
        return ingredientRepo.findById(source).orElse(null);  //вот как ищем по ID ингредиенты
//        return ingredientMap.get(source); //по id ключ
    }

}
