package com.hf.mongodb;

import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.plugin.monogodb.MongoKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

public class MongoController extends Controller {

    public void index() {

        // Map<String, Object> filter = new HashMap<String, Object>();
        // filter.put("name", "haha") ; //精确过滤
        // Map<String, Object> like = new HashMap<String, Object>();
        // like.put("name","haha"); //模糊匹配，相当于sql 的like %zhang%
        // Map<String, Object> sort = new HashMap<String, Object>();
        // sort.put("id", "desc"); // 排序
        // Page<Record> page = MongoKit.paginate("movie", 1, 3);
        setAttr("Page", MongoKit.paginate("movie", getParaToInt(0, 1), 3));
        render("mongo.html");
    }

    public void add() {

    }

    @Before(MongoValidator.class)
    public void save() {
        String name = getPara("name");
        Record record = new Record().set("name", name);
        MongoKit.save("movie", record);
        redirect("/");
    }

    public void delete() {
        MongoKit.remove("movie", new BasicDBObject("_id", new ObjectId(getPara())));
        redirect("/");
    }

    public void edit() {
        /*
         * Map<String, Object> filter = new HashMap<String, Object>(); filter.put( "_id", new
         * ObjectId(getPara())); Page<Record> page = MongoKit.paginate("movie", 1, 10, filter);
         * setAttr("this", page);
         */
        DBCollection movie = MongoKit.getCollection("movie");
        setAttr("this", movie.findOne(new ObjectId(getPara())));

    }

    public void update() {
        Map<String, Object> src = new HashMap<String, Object>();
        src.put("_id", new ObjectId(getPara("id")));
        String name = getPara("name");
        Map<String, Object> desc = new HashMap<String, Object>();
        desc.put("name", name);
        MongoKit.updateFirst("movie", src, desc); // 只能修改符合条件的第一条数据..
        redirect("/");

    }

}
