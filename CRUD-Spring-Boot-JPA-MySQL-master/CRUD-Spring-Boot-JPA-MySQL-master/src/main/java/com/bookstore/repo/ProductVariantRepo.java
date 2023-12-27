package com.bookstore.repo;

import com.bookstore.entity.ProductVariant;
import com.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductVariantRepo extends JpaRepository<ProductVariant, Long>, PagingAndSortingRepository<ProductVariant, Long> {
    Optional<User> findById(Long id);
    Optional<ProductVariant> findBySizeIdAndColorIdAndProductId(
            @Param("sizeId") Long sizeId,
            @Param("colorId") Long colorId,
            @Param("productId") Long productId
    );
    Optional<ProductVariant> findBySizeIdAndColorId(
            @Param("sizeId") Long sizeId,
            @Param("colorId") Long colorId
    );
    @Query(value = "select ac.id as id," +
            " ac.productId  as productId ," +
            " ac.colorId as colorId," +
            " ac.sizeId as sizeId," +
            " ac.stock as stock," +
            " ac.price as price," +
            " ac.soldQuantity as soldQuantity," +
            " ac.cartQuantity as cartQuantity" +
            " from Productvariants as ac" +
            " where" +
            " ac.productId = :productId "
    )
    List<Map<String, Object>> getByProductId(
            @Param("productId") Long productId
            );

    @Query(
            value = "SELECT DISTINCT " +
                    " pv.color_id,   " +
                    " cl.name " +
                    " FROM Productvariants pv " +
                    " LEFT JOIN Colors cl ON pv.color_id = cl.id  " +
                    " WHERE pv.product_id = :productId" +
                    " order by pv.color_id asc"
            ,
            nativeQuery = true
    )
    List<Object[]> getColorByProductId(@Param("productId") Long productId);

    @Query(
            value = "SELECT DISTINCT " +
                    " pv.size_id,   " +
                    " cl.name " +
                    " FROM Productvariants pv " +
                    " LEFT JOIN Sizes cl ON pv.size_id = cl.id  " +
                    " WHERE pv.product_id = :productId" +
                    " order by pv.size_id asc"
            ,
            nativeQuery = true
    )
    List<Object[]> getSizeByProductId(@Param("productId") Long productId);

    @Query(
            value = "SELECT DISTINCT " +
                    " pv.size_id,   " +
                    " pv.color_id,   " +
                    " si.name as sizeName, " +
                    " cl.name as colorName " +
                    " FROM Productvariants pv " +
                    " LEFT JOIN Sizes si ON pv.size_id = si.id  " +
                    " LEFT JOIN Colors cl ON pv.color_id = cl.id  " +
                    " WHERE pv.product_id = :productId" +
                    " order by pv.size_id asc"
            ,
            nativeQuery = true
    )
    List<Object[]> getSizeAndColorByProductId(@Param("productId") Long productId);
}

