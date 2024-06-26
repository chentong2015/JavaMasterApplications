package com.entity.manager.query;

import com.entity.manager.entity.Sample;
import com.entity.manager.entity.NamedQueryEntity;
import com.entity.manager.util.EntityManagerHandler;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

// Hibernate JPA API (功能较少, 性能较低): 不推荐使用
// 1. HqlQuery      提供标准的HQL查询字符串
// 2. CriteriaQuery 创建一个查询的标准，指定要查询的数据类型和Selection返回的字段，指定查询策略
// 3. NativeQuery   提供Jpa native sql原生的sql查询语句
// https://www.baeldung.com/hibernate-entitymanager
public class JpaEntityManagerQueries {

    public static void main(String[] args) {
        testHqlSelectQuery();
        // testHqlDeleteQuery();
        // testNativeSqlQueries();
    }

    // TODO. @Entity(name 推荐设置
    // 1. 当没有设置entity name时，.getSimpleName()和.getName()都能工作
    // 2. 当设置entity name之后，HQL查询语句必须使用全路径名称.getName()
    private static void testHqlSelectQuery() {
        EntityManager entityManager = EntityManagerHandler.getEntityManager();
        String qlString = "FROM " + Sample.class.getName();

        // 3. 创建HQL Query查询时需要提供ResultClass结果值的类型 !!
        List<Sample> sampleList = entityManager.createQuery(qlString, Sample.class).getResultList();
        System.out.println(sampleList.size());
    }

    private static void testHqlDeleteQuery() {
        EntityManager entityManager = EntityManagerHandler.getEntityManager();
        entityManager.getTransaction().begin();
        // 等效于class.getName()返回全路径
        String fullPath = Sample.class.getCanonicalName();

        String deleteQuery = "DELETE FROM " + fullPath + " p WHERE p.name = :name";
        entityManager.createQuery(deleteQuery).setParameter("name", "test").executeUpdate();
        entityManager.getTransaction().commit();
    }


    // TODO. 使用CriteriaQuery.createQuery(Sample.class)来创建条件查询和Entity Name没有关系 !!
    private static void testCriteriaQuery() {
        EntityManager entityManager = EntityManagerHandler.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Sample> criteriaQuery = criteriaBuilder.createQuery(Sample.class);

        Root<Sample> root = criteriaQuery.from(Sample.class);
        // criteriaQuery.select(root.get("id"));
        criteriaQuery.where(criteriaBuilder.equal(root.get("name"), "java"));
        criteriaQuery.distinct(true);
        // criteriaQuery.having(predicate..);
        List<Sample> sampleList = entityManager.createQuery(criteriaQuery).getResultList();
        // .setLockMode(LockModeType.OPTIMISTIC) 没有transaction则不需要设置锁模式

        entityManager.close();
        for (Sample sample : sampleList) {
            System.out.println(sample.getId());
        }
    }

    // TODO. 提供查询出来的结果对应的类型，如果是原实体类型，则必须查询出所有的字段
    public static void testNativeSqlQueries() {
        EntityManager entityManager = EntityManagerHandler.getEntityManager();
        String sqlString = "SELECT * FROM t_sample_entity";
        List<Sample> sampleList = entityManager.createNativeQuery(sqlString, Sample.class).getResultList();
        for (Sample sample : sampleList) {
            System.out.println(sample);
        }
        entityManager.close();
    }

    // TODO. 同时支持@NamedQuery和@NamedNativeQuery
    // 通过实体类型上定义的NamedQuery具名查询来查询数据
    public static void testNamedQueries() {
        EntityManager entityManager = EntityManagerHandler.getEntityManager();
        List<NamedQueryEntity> results = entityManager.createNamedQuery("EntityNamed.findAll").getResultList();
        // List<NamedQueryEntity> results = entityManager.createNamedQuery("EntityNamedNative.findAll").getResultList();
        for (NamedQueryEntity entity : results) {
            System.out.println(entity);
        }
        entityManager.close();
    }
}
