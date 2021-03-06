package com.chryl.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**--------------------------注意这里没用日期类型
 * 搜索中的商品信息
 * 不需要中文分词的字段设置成@Field(type = FieldType.Keyword)类型，//不会进行分词建立索引的类型
 * 需要中文分词的设置成@Field(analyzer = "ik_max_word",type = FieldType.Text)类型。
 * <p>
 * //为文档自动指定元数据类型
 * Text,    //会进行分词并建了索引的字符类型
 * Integer,
 * Long,
 * Date,
 * Float,
 * Double,
 * Boolean,
 * Object,
 * Auto,    //自动判断字段类型
 * Nested,  //嵌套对象类型
 * Ip,
 * Attachment,
 * Keyword  //不会进行分词建立索引的类型
 */
//@Document 作用在类，标记实体类为文档对象，一般有四个属性
//        indexName：对应索引库名称
//        type：对应在索引库中的类型
//        shards：分片数量，默认5
//        replicas：副本数量，默认1
//@Id 作用在成员变量，标记一个字段作为id主键
//@Field 作用在成员变量，标记为文档的字段，并指定字段映射属性：
//        type：字段类型，取值是枚举：FieldType是枚举：FieldType，可以是text、long、short、date、integer、object等
//            text：存储数据时候，会自动分词，并生成索引
//            keyword：存储数据时候，不会分词建立索引
//            Numerical：数值类型，分两类
//                基本数据类型：long、interger、short、byte、double、float、half_float
//                浮点数的高精度类型：scaled_float
//                需要指定一个精度因子，比如10或100。elasticsearch会把真实值乘以这个因子后存储，取出时再还原。
//            Date：日期类型
//                elasticsearch可以对日期格式化为字符串存储，但是建议我们存储为毫秒值，存储为long，节省空间。
//        index：是否索引，布尔类型，默认是true
//        store：是否存储，布尔类型，默认是false
//        analyzer：分词器名称：ik_max_word
@Document(indexName = "pms", type = "product", shards = 1, replicas = 0)
public class EsProduct implements Serializable {
    private static final long serialVersionUID = -1L;
    @Id//id 字段
    private Long id;
    @Field(type = FieldType.Keyword)//不中文分词
    private String productSn;
    private Long brandId;
    @Field(type = FieldType.Keyword)
    private String brandName;
    private Long productCategoryId;
//    ik_max_word 和 ik_smart 什么区别?
//    ik_max_word: 会将文本做最细粒度的拆分，比如会将“中华人民共和国国歌”拆分为“中华人民共和国,中华人民,中华,华人,人民共和国,人民,人,民,共和国,共和,和,国国,国歌”，会穷尽各种可能的组合，适合 Term Query；
//    ik_smart: 会做最粗粒度的拆分，比如会将“中华人民共和国国歌”拆分为“中华人民共和国,国歌”，适合 Phrase 查询。
    @Field(type = FieldType.Keyword)//不会进行分词建立索引的类型
    private String productCategoryName;
    private String pic;
    @Field(analyzer = "ik_max_word", type = FieldType.Text)//中文分词
    private String name;
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String subTitle;
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String keywords;
    private BigDecimal price;
    private Integer sale;
    private Integer newStatus;
    private Integer recommandStatus;
    private Integer stock;
    private Integer promotionType;
    private Integer sort;
    @Field(type = FieldType.Nested)//嵌套的对象
    private List<EsProductAttributeValue> attrValueList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductSn() {
        return productSn;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public Integer getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Integer newStatus) {
        this.newStatus = newStatus;
    }

    public Integer getRecommandStatus() {
        return recommandStatus;
    }

    public void setRecommandStatus(Integer recommandStatus) {
        this.recommandStatus = recommandStatus;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(Integer promotionType) {
        this.promotionType = promotionType;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<EsProductAttributeValue> getAttrValueList() {
        return attrValueList;
    }

    public void setAttrValueList(List<EsProductAttributeValue> attrValueList) {
        this.attrValueList = attrValueList;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return "EsProduct{" +
                "id=" + id +
                ", productSn='" + productSn + '\'' +
                ", brandId=" + brandId +
                ", brandName='" + brandName + '\'' +
                ", productCategoryId=" + productCategoryId +
                ", productCategoryName='" + productCategoryName + '\'' +
                ", pic='" + pic + '\'' +
                ", name='" + name + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", keywords='" + keywords + '\'' +
                ", price=" + price +
                ", sale=" + sale +
                ", newStatus=" + newStatus +
                ", recommandStatus=" + recommandStatus +
                ", stock=" + stock +
                ", promotionType=" + promotionType +
                ", sort=" + sort +
                ", attrValueList=" + attrValueList +
                '}';
    }
}
