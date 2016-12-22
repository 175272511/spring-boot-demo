package com.example;

import com.example.demo.domain.TAuthUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;


/**
 * Created by liuhui on 2016/12/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@WebAppConfiguration
public class TestElasticsearch extends DemoApplicationTests {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @Test
    public void testElasticsearch(){

        StringQuery stringQuery = new StringQuery("{\"query\":{\"match\":{\"OPENID\":\"ovaDDjuXSLBm4y8pCkXFjeBOjHv4\"}}}");
        Pageable pageable = new PageRequest(0, 1000);
        CriteriaQuery criteriaQuery = new CriteriaQuery(new Criteria("FANS_NAME").contains("中国梦"), pageable);
//        List<StoreFansDetail> storeFansDetails = elasticsearchTemplate.queryForList(stringQuery, StoreFansDetail.class);
        List<StoreFansDetail> storeFansDetails = elasticsearchTemplate.queryForList(criteriaQuery, StoreFansDetail.class);
//        Page<StoreFansDetail> storeFansDetails = elasticsearchTemplate.queryForPage(criteriaQuery, StoreFansDetail.class);
        System.out.println(storeFansDetails);
    }
}
