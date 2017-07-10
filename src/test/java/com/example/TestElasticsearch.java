package com.example;

import com.alibaba.fastjson.JSON;
import com.example.demo.domain.TAuthUser;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.*;


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
//
//        StringQuery stringQuery = new StringQuery("{\"query\":{\"match\":{\"OPENID\":\"ovaDDjuXSLBm4y8pCkXFjeBOjHv4\"}}}");
//        Pageable pageable = new PageRequest(0, 1000);
//        CriteriaQuery criteriaQuery = new CriteriaQuery(new Criteria("FANS_NAME").contains("中国梦"), pageable);
////        List<StoreFansDetail> storeFansDetails = elasticsearchTemplate.queryForList(stringQuery, StoreFansDetail.class);
//        List<StoreFansDetail> storeFansDetails = elasticsearchTemplate.queryForList(criteriaQuery, StoreFansDetail.class);
////        Page<StoreFansDetail> storeFansDetails = elasticsearchTemplate.queryForPage(criteriaQuery, StoreFansDetail.class);
//        System.out.println(storeFansDetails);
        long start1 = System.currentTimeMillis();
        CriteriaQuery criteriaQuery = new CriteriaQuery(new Criteria("id").is("0"));
//        elasticsearchTemplate.delete(criteriaQuery, StoreFansDetail.class);
//        elasticsearchTemplate.refresh(StoreFansDetail.class);
        Long a = elasticsearchTemplate.count(criteriaQuery, StoreFansDetail.class);
        System.out.println(a);
        System.out.println("删除耗时：" + (System.currentTimeMillis() - start1));
        System.out.println("开始");
        long start = System.currentTimeMillis();
//        for (int i = 0; i< 1000000; i++){

            StoreFansDetail storeFansDetail = new StoreFansDetail();
            storeFansDetail.setActDate(new Date());
            storeFansDetail.setChannel("测试");
            storeFansDetail.setId(0);
            storeFansDetail.setFansHead("但是房贷首付二");
            storeFansDetail.setFansName("超大字数反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复" +
                    "反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复" +
                    "反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复" +
                    "反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复" +
                    "反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复" +
                    "反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复" +
                    "反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复" +
                    "反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复" +
                    "反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复" +
                    "反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复" +
                    "反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复" +
                    "反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复" +
                    "反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复" +
                    "反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复" +
                    "反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复" +
                    "反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复" +
                    "反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复" +
                    "反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复反反复复" );
            storeFansDetail.setFansMode("佛挡杀佛");
            storeFansDetail.setFansModeF("fffffffffffff");
            storeFansDetail.setFansModeL("llllllllllllllllllll");
            storeFansDetail.setFansModeZ("zzzzzzzzzzzzzzzzzzzzzzzz");
            storeFansDetail.setFansSum("dfddddddddddddd");
            storeFansDetail.setMchUuid("1111111111111111111");
            storeFansDetail.setOpenid("ddddddddddddddddddddddd");
            storeFansDetail.setIsAtt("ddddddddddddddddddddddddddddddddddd");
            storeFansDetail.setProvinceId(1111111);
            storeFansDetail.setVersion(45324124L);
            storeFansDetail.setStoreCity("fdsfdsfdsfds");
            storeFansDetail.setTest("测试加字段");
            storeFansDetail.setTest1("fsdewqewqfdes");
            storeFansDetail.setTest2("fdsewqewqfds");
            storeFansDetail.setTest3("ewewq");
            IndexQuery indexQuery =  new IndexQuery();
            indexQuery.setId(1000002+"");
            indexQuery.setObject(storeFansDetail);
//            elasticsearchTemplate.index(indexQuery);
            elasticsearchTemplate.update(new UpdateQueryBuilder().withId(1000002+"").withIndexRequest(new IndexRequest().source(JSON.toJSONString(storeFansDetail))).withClass(StoreFansDetail.class).withDoUpsert(true).build());
//            elasticsearchTemplate.refresh(StoreFansDetail.class);
//        }

        System.out.println("插入耗时：" + (System.currentTimeMillis() - start));
        criteriaQuery = new CriteriaQuery(new Criteria("id").is("0"));
        Long b = elasticsearchTemplate.count(criteriaQuery, StoreFansDetail.class);
        System.out.println(b);

    }

    @Test
    public void testAdd(){
//        elasticsearchTemplate.deleteIndex(StoreFansDetail.class);
        String setting = "{\n" +
                "    \"index\": {\n" +
                "        \"analysis\": {\n" +
                "            \"analyzer\": {\n" +
                "                \"ik_pinyin_analyzer\": {\n" +
                "                    \"type\": \"custom\",\n" +
                "                    \"tokenizer\": \"ik_smart\",\n" +
                "                    \"filter\": [\"my_pinyin\", \"word_delimiter\"]\n" +
                "                }\n" +
                "            },\n" +
                "            \"filter\": {\n" +
                "                \"my_pinyin\": {\n" +
                "                    \"type\": \"pinyin\",\n" +
                "                    \"first_letter\": \"prefix\",\n" +
                "                    \"padding_char\": \" \"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";
        elasticsearchTemplate.createIndex(StoreFansDetail.class, setting);
        StoreFansDetail storeFansDetail = new StoreFansDetail();
        storeFansDetail.setStoreCity("中国人民万岁");
        storeFansDetail.setMchUuid("123123");
        elasticsearchTemplate.putMapping(StoreFansDetail.class);
        elasticsearchTemplate.index(new IndexQueryBuilder().withId("1").withObject(storeFansDetail).build());
    }

    @Test
    public void testQuery(){
        Criteria criteria = new Criteria("storeCity").expression("zhon");
        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery("storeCity.pinyin","zgrmws")).build();
        List<StoreFansDetail> storeFansDetails = elasticsearchTemplate.queryForList(criteriaQuery, StoreFansDetail.class);
        System.out.println(storeFansDetails);
    }


}
