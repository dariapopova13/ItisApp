package com.university.itis.itisapp.dao.impl;

import com.university.itis.itisapp.dao.SearchDao;
import com.university.itis.itisapp.model.News;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository(value = "newsSearchDao")
@Transactional
public class NewsSearchDao implements SearchDao<News> {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public List<News> search(String request) throws InterruptedException {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        fullTextEntityManager.createIndexer().startAndWait();
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(News.class).get();
        org.apache.lucene.search.Query query = queryBuilder
                .keyword()
                .onFields("newsText", "title")
                .matching(request)
                .createQuery();


        javax.persistence.Query persistenceQuery = fullTextEntityManager.
                createFullTextQuery(query, News.class);
        List<News> result = persistenceQuery.getResultList();

        return result;
    }
}
