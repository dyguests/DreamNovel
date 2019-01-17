package com.fanhl.dreamnovel.bizwriting.database.dao;

import com.fanhl.dreamnovel.bizwriting.database.entity.Article;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ArticleDao {
    @Insert
    void insertAll(Article... articles);

    @Delete
    void delete(Article article);

//    @Update
//    void update(Article article);

    @Query("SELECT * FROM article")
    List<Article> getAll();
}
