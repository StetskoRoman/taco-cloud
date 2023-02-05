package sia.tacocloud.takoBase.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sia.tacocloud.takoBase.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@Repository
public class JdbcIngredientRepository implements IngredientRepository{

    private JdbcTemplate jdbcTemplate; //встроенная БД получается
    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Ingredient> findAll() {
    //запрос к БД будет просто
        return jdbcTemplate.query(
                "select id, name, type from Ingredient",
                this::mapRowToIngredient); //каждого переведет в ингредиент
    }

    private Ingredient mapRowToIngredient(ResultSet row, int rowNum) throws SQLException { //это по сути RowMapper, там вся строка и ее номер

            return new Ingredient(
                    row.getString("id"),
                    row.getString("name"),
                    Ingredient.Type.valueOf(row.getString("type")));
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        List<Ingredient> results = jdbcTemplate.query(
                "select id, name, type from Ingredient where id=?",  //? замещается значением id в методе
                this::mapRowToIngredient,
                id); //аргумент передается и по нему ищет ингредиент
        return results.size() == 0 ?
                Optional.empty() :
                Optional.of(results.get(0));
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        //вставить в таблицу ингредиентов новый ингредиент
        jdbcTemplate.update(
                "insert into Ingredient (id, name, type) values (?,?,?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());

        return ingredient;
    }
}
