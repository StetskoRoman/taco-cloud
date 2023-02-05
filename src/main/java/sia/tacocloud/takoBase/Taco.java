package sia.tacocloud.takoBase;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Taco {

    private Long id; //для хранения и извлечения тако
    private Date createdAt = new Date();

    @NotNull
    @Size(min = 5, message = "name must be 5 characters long and more")
    private String name;
    @NotNull
    @Size(min = 1, message = "at least 1 ingredient must be")
    private List<Ingredient> ingredients;
}
