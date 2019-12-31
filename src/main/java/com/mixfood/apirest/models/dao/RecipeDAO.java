package com.mixfood.apirest.models.dao;

import com.mixfood.apirest.entity.Recipe;
import com.mixfood.apirest.entity.User;
import com.mixfood.apirest.projections.RecipeCard;
import com.mixfood.apirest.projections.RecipeLatest;
import com.mixfood.apirest.projections.RecipeLatestUser;
import com.mixfood.apirest.projections.RecipeSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeDAO extends JpaRepository<Recipe,Integer>
{
   /* @Query(value =  "SELECT r.id_recipe AS id, r.name AS name, r.create_at AS createAt, r.averange_ranking AS averangeRanking, r.thumb_route AS thumbRoute, r.user_id AS idUser, CONCAT(u.name,' ', u.lastname) AS username, r.category_id AS idCategory, c.name AS categoryName FROM Recipes r \n" +
            "INNER JOIN users u ON u.id_user = r.user_id " +
            "INNER JOIN categories c ON r.category_id = c.id_category ORDER BY r.create_at DESC LIMIT 10",
            nativeQuery = true)*/
    //@Query("SELECT r.id_recipe AS id, r.name AS name, r.create_at AS createAt, r.averange_ranking AS averangeRanking, r.thumb_route AS thumbRoute, r.user_id AS idUser FROM Recipes r ")
//    public List<RecipeCard> findAllForCards();

    //*Get Recents recipes
    @Query("SELECT r FROM Recipe r WHERE status = 'public' ORDER BY createAt DESC")
    public List<RecipeCard> findAllForCards(Pageable pageable);

    //*Find recipes by name
    @Query(value = "SELECT id_recipe AS id, name FROM recipes WHERE name LIKE :term%", nativeQuery = true)
    public List<RecipeSearch> findLikeName(String term);

    //*Find recents recipes by id user

    @Query("SELECT r FROM Recipe r WHERE Status = 'public' ORDER BY createAt DESC")
    public List<RecipeLatest> findRecentsByIdUser(int id, Pageable pageable);

    //*Find featured recipes
    @Query("SELECT r FROM Recipe r WHERE (averangeRanking >= 3 AND Status = 'public') AND user_id = :id ORDER BY views DESC")
    public List<RecipeLatest> findCardsByAverangeRankingAndIdUser(int id, Pageable pageable);

    //*Find recipes by Name and Category
    @Query("SELECT r FROM Recipe r WHERE category_id = :idCategory AND name LIKE :term%")
    public Page<RecipeCard> findAllByName(String term, int idCategory, Pageable pageable);

    //*Find latests recipes by user
    @Query("SELECT r FROM Recipe  r WHERE user_id = :id")
    public List<RecipeLatestUser> findLatestsByIdUser(int id, Pageable pageable);

}
