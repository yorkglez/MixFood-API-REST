package com.mixfood.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.mixfood.apirest.entity.Tag;

public interface TagDao extends CrudRepository<Tag,Integer>
{

}