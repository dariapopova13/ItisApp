package com.university.itis.itisapp.dao.impl;

import com.university.itis.itisapp.dao.SearchDao;
import com.university.itis.itisapp.dto.NewsFilterDto;
import com.university.itis.itisapp.model.News;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
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

    @Override
    public List<News> filter(NewsFilterDto filter) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<News> query = builder.createQuery(News.class);
        Root<News> root = query.from(News.class);

        List<Predicate> predicates = new ArrayList<>();
        if (filter.getFrom() != null)
            predicates.add(builder.greaterThanOrEqualTo(root.get("deadline"), filter.getFrom()));
        if (filter.getTo() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("deadline"), filter.getTo()));
        }
        if (filter.getYear() != null) {
            Expression<Integer> expression = root.get("year");
            predicates.add(expression.in(filter.getYear()));
        }

        query.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
        return entityManager.createQuery(query.select(root)).getResultList();
    }
}
