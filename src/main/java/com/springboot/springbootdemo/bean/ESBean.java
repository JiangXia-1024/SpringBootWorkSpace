package com.springboot.springbootdemo.bean;


import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


import java.io.Serializable;

/**
 * @Auther: 江夏
 * @Date: 2022/01/06/15:09
 * @Description:es测试使用实体类,需要实现Serializable接口
 */
@Data ////通过这个注解，可以不用写gettersetter方法
@ToString
@Document(indexName = "esdoc") //通过这个注解可以声明一个文档，指定其所在的索引库
public class ESBean implements Serializable {

    //这里必须指定一个id
    @Id
    private int id;

    // 这里配置了分词器，字段类型，可以不配置，默认也可
//    @Field(analyzer = "ik_smart",type = FieldType.Text)
    private String name;

    //    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private Integer genter;

    //    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String desc;

    public ESBean() {
    }

    public ESBean(int id, String name, Integer genter, String desc) {
        this.id = id;
        this.name = name;
        this.genter = genter;
        this.desc = desc;
    }
}

