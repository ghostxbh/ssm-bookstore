package cn.jkj521.bookstore.dao;

import cn.jkj521.bookstore.entity.ProductImage;
import cn.jkj521.bookstore.util.PageUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductImageMapper {
    Integer insertOne(@Param("productImage") ProductImage productImage);

    Integer insertList(@Param("productImage_list") List<ProductImage> productImageList);

    Integer updateOne(@Param("productImage") ProductImage productImage);

    Integer delete(@Param("productImage_id_list") Integer[] productImage_id_list);

    List<ProductImage> select(@Param("product_id") Integer product_id, @Param("productImage_type") Byte productImage_type, @Param("pageUtil") PageUtil pageUtil);

    ProductImage selectOne(@Param("productImage_id") Integer productImage_id);

    Integer selectTotal(@Param("product_id") Integer product_id, @Param("productImage_type") Byte productImage_type);
}
