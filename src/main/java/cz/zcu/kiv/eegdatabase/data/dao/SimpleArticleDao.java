/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.zcu.kiv.eegdatabase.data.dao;

import cz.zcu.kiv.eegdatabase.data.pojo.Article;
import cz.zcu.kiv.eegdatabase.data.pojo.Person;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jiri Vlasimsky
 */
public class SimpleArticleDao<T, PK extends Serializable>
        extends SimpleGenericDao<T, PK> implements ArticleDao<T, PK> {
    public SimpleArticleDao(Class<T> type) {
        super(type);
    }

    public List<Article> getAllArticles() {
        String HQLSelect = "from Article order by time desc";
        List<Article> articles = getHibernateTemplate().find(HQLSelect);
        return articles;
    }

    @Override
    public List getArticlesForUser(Person person) {
        String query;
        List articles = null;

        if (person.getAuthority().equals("ROLE_ADMIN")) {
            // We can simply load the newest articles
            query = "from Article a left join fetch a.researchGroup r " +
                    "order by a.time desc";
            articles = getHibernateTemplate().find(query);
        } else {
            // We need to load only articles which can be viewed by the logged user.
            // That is, we need to load only public articles or articles from the groups the logged user is member of.
            query = "from Article a left join fetch a.researchGroup r " +
                    "where " +
                    "a.researchGroup.researchGroupId is null or " +
                    "a.researchGroup.researchGroupId in " +
                    "(select rm.id.researchGroupId from ResearchGroupMembership rm where rm.id.personId = :personId) " +
                    "order by a.time desc";
            articles = getHibernateTemplate().findByNamedParam(query, "personId", person.getPersonId());
        }

        return articles;
    }

    @Override
    public List getArticlesForUser(Person person, int limit) {
        getHibernateTemplate().setMaxResults(limit);
        List articles = getArticlesForUser(person);
        getHibernateTemplate().setMaxResults(0);
        return articles;
    }

    @Override
    public List getArticlesForList(Person person, int min, int count) {
        String query;
        List articles = null;

        if (person.getAuthority().equals("ROLE_ADMIN")) {
            // We can simply load the newest articles
            query = "from Article a left join fetch a.researchGroup r join fetch a.person p " +
                    "order by a.time desc";
            articles = getSession().createQuery(query).setFirstResult(min).setMaxResults(count).list();
        } else {
            // We need to load only articles which can be viewed by the logged user.
            // That is, we need to load only public articles or articles from the groups the logged user is member of.
            query = "from Article a left join fetch a.researchGroup r join fetch a.person p " +
                    "where " +
                    "a.researchGroup.researchGroupId is null or " +
                    "a.researchGroup.researchGroupId in " +
                    "(select rm.id.researchGroupId from ResearchGroupMembership rm where rm.id.personId = :personId) " +
                    "order by a.time desc";
            articles = getSession().createQuery(query).setFirstResult(min).setMaxResults(count).setParameter("personId", person.getPersonId()).list();
        }

        return articles;
    }

    @Override
    public int getArticleCountForPerson(Person person) {
        if (person.getAuthority().equals("ROLE_ADMIN")) {
            return ((Long) getSession().createQuery("select count(*) from Article").uniqueResult()).intValue();
        }
        String query = "select count(*) from Article a left join a.person p where " +
                "a.researchGroup.researchGroupId is null or " +
                "a.researchGroup.researchGroupId in " +
                "(select rm.id.researchGroupId from ResearchGroupMembership rm where rm.id.personId = :personId)";
        return ((Long) getSession().createQuery(query).setParameter("personId", person.getPersonId()).uniqueResult()).intValue();
    }

    /**
     * Gets article detail information for article detail page. Check the correct permission of the user to view
     * requested article.
     *
     * @param id           Id of the requested article
     * @param loggedPerson User whose permission is checked - should be logged user
     * @return If the user is permitted to view the article specified by id the Article object is returned. Otherwise, null is returned.
     */
    @Override
    public Article getArticleDetail(int id, Person loggedPerson) {

        if (loggedPerson.getAuthority().equals("ROLE_ADMIN")) {
            String query = "from Article a join fetch a.person left join fetch a.researchGroup " +
                    "where " +
                    "a.articleId = :id";
            return (Article) getSession().createQuery(query).setParameter("id", id).uniqueResult();
        } else {
            String query = "from Article a join fetch a.person left join fetch a.researchGroup " +
                    "where " +
                    "a.articleId = :id and (" +
                    "a.researchGroup.researchGroupId is null or " +
                    "a.researchGroup.researchGroupId in " +
                    "(select rm.id.researchGroupId from ResearchGroupMembership rm where rm.id.personId = :personId))";
            return (Article) getSession().createQuery(query).setParameter("id", id).setParameter("personId", loggedPerson.getPersonId()).uniqueResult();
        }
    }

    public List<Article> getAllUserArticles() {
        String HQLSelect = "from Article order by time desc";
        List<Article> articles = getHibernateTemplate().find(HQLSelect);
        return articles;
    }

}
