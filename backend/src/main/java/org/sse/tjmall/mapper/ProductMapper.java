package org.sse.tjmall.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.sse.tjmall.dto.Product;

import java.util.List;

@Component
@Mapper
public interface ProductMapper {

    @Select("select * from product;")
    List<Product> getAllProduct();

    @Select("select * from product where productId = #{productId};")
    Product getProductById(@Param("productId") int productId);

    @Select("select * from product where product.name like #{keyword};")
    List<Product> getProductByKeyword(@Param("keyword") String keyword);
}
