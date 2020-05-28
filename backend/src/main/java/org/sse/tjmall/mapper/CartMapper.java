package org.sse.tjmall.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.sse.tjmall.dto.CartItem;

import java.util.List;

@Component
@Mapper
public interface CartMapper {

    @Insert("insert into cart(username, productId, number)\n" +
            "value (#{username}, #{productId}, 1);")
    void addNewItemToCart(@Param("username") String username,
                          @Param("productId") int productId);

    @Select("select * from cart\n" +
            "where cart.username = #{username} and cart.productId = #{productId};")
    CartItem isItemAdded(@Param("username") String username,
                         @Param("productId") int productId);

    @Update("update cart\n" +
            "set cart.number = cart.number + 1\n" +
            "where cart.username = #{username} and cart.productId = #{productId};")
    void addExistItemToCart(@Param("username") String username,
                            @Param("productId") int productId);

    @Update("update cart\n" +
            "set cart.number = cart.number - 1\n" +
            "where cart.username = #{username} and cart.productId = #{productId};")
    void removeExistItemOne(@Param("username") String username,
                            @Param("productId") int productId);

    @Delete("delete from cart\n" +
            "where username = #{username} and productId = #{productId};")
    void removeExistItemAll(@Param("username") String username,
                            @Param("productId") int productId);

    @Select("select username, number, cart.productId, name, originalPrice, newPrice, imageUrl, detailUrls\n" +
            "from cart left join product p on cart.productId = p.productId\n" +
            "where username = #{username};")
    List<CartItem> getAllByUsername(@Param("username") String username);
}
