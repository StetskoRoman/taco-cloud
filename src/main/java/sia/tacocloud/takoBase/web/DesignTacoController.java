package sia.tacocloud.takoBase.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.stream.Collectors;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.takoBase.Ingredient;
import sia.tacocloud.takoBase.Ingredient.Type;
import sia.tacocloud.takoBase.*;
import sia.tacocloud.takoBase.data.IngredientRepository;

import java.util.Arrays;
import java.util.List;

@Slf4j //простой логгин в lombok
@Controller
@RequestMapping("/design") //на уровне класса-значит design путь ко всем Mapping-ам ниже добавится
@SessionAttributes("tacoOrder") //объект tacoOrder поддерживается на уровне сеанса, а в сеансе формируется заказ из списка тако
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;
    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }
    @ModelAttribute
    public void addIngredientsToModel(Model model) {  //метод достает все ингредиенты и сортирует их по типу
        Iterable<Ingredient> ingredients = ingredientRepo.findAll();
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType((List<Ingredient>) ingredients, type));
        }
    }

    //старая версия без БД
//    @ModelAttribute  //Model-пойдет инфа в представление
//    public void addIngredientsToModel(Model model) {
//
//        List<Ingredient> ingredients = Arrays.asList(    //список ингредиентов лучше из ДБ брать
//                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
//                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
//                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
//                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
//                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
//                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
//                new Ingredient("CHED", "Cheddar", Type.CHEESE),
//                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
//                new Ingredient("SLSA", "Salsa", Type.SAUCE),
//                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
//
//        );
//
//        Type[] types = Type.values(); //просто вывод типов ингредиентов будет отсортирован в отображении
//        for (Type type : types) {
//            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type)); //
//        }
//    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping//когда по адресу /design пойдут, метод откроет стр design(представление)
    public String showDesignForm() {
        return "design";
    }

    @PostMapping  //значит этот метод обрабатывает запросы POST по пути /design @RequestMapping("/design")
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {   //Taco и TacoOrder пустые были созданы выше при методе GET
        if (errors.hasErrors()) {  //проверит taco до начала метода processTaco
            return "design";
        }
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {

        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

}
