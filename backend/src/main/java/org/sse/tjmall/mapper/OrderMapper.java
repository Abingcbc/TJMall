package org.sse.tjmall.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.sse.tjmall.dto.Order;

import java.util.List;

@Component
@Mapper
public interface OrderMapper {

    @Select("select orderId, username, number, createTime, `order`.productId, name, originalPrice, newPrice, imageUrl, detailUrls\n" +
            "from `order` left join product p on `order`.productId = p.productId\n" +
            "where `order`.username = #{username};")
    List<Order> getOrderByUsername(@Param("username") String username);

    @Insert("insert into `order`(username, productId, number, createTime)\n" +
            "value (#{username}, #{productId}, #{number}, NOW());")
    void createNewOrder(@Param("username") String username,
                        @Param("productId") int productId,
                        @Param("number") int number);
}
