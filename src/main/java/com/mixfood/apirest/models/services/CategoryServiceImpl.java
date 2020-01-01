package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.Category;
import com.mixfood.apirest.models.dao.CategoryDAO;
import com.mixfood.apirest.projections.CategoryCard;
import com.mixfood.apirest.projections.CategoryList;
import com.mixfood.apirest.projections.CategoryName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService
{
    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    @Transactional
    public List<Category> findAll() {
        return (List<Category>) categoryDAO.findAll();
    }

    @Override
    public Category findById(int id) {
        return categoryDAO.findById(id).orElse(null);
    }

    @Override
    public Category save(Category category) {
        return categoryDAO.save(category);
    }

    @Override
    public void delete(int id) {
        categoryDAO.deleteById(id);
    }

    @Override
    public List<CategoryCard> findAllForCards() {
        return categoryDAO.findAllForCards();
    }

    @Override
    public List<CategoryList> findAllForList() {
        return categoryDAO.findAllForList();
    }

    @Override
    public List<CategoryList> findListByIdUser(int id, Pageable pageable) {
        return categoryDAO.findListByIdUser(id,pageable);
    }

    @Override
    public CategoryName findNameById(int id) {
        return categoryDAO.findNameById(id);
    }
}
