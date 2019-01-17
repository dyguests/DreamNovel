package com.fanhl.dreamnovel.database;

import com.fanhl.dreamnovel.bizwriting.database.dao.ArticleDao;
import com.fanhl.dreamnovel.bizwriting.database.entity.Article;

import androidx.room.Database;

@Database(entities = {Article.class}, version = 1)
public abstract class AppDatabase implements IAppDatabase {
    public abstract ArticleDao articleDao();
}
