package com.bookstore.repo;

import com.bookstore.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductRepo extends PagingAndSortingRepository<Product, Integer> {
    Optional<Product> findById(Integer id);

    List<Product> findByStatus(boolean status, Pageable pageable);

    @Query(value = "select ac.id as id," +
            " ac.name as name," +
            " ac.price as price," +
            " ac.discount as discount," +
            " ac.link as link," +
            " ac.description as description," +
            " ac.status as status" +
            " from Product as ac" +
            " where" +
            " ac.status = :status" +
//            " and (lower(concat(coalesce(ac.name, ''), coalesce(ac.author, '')))" +
            " and (lower(concat(coalesce(ac.name, '')))" +
            " like (lower(concat('%',:search,'%'))) " +
            " or (coalesce(:search, '#') = '#')) " )
    Page<Map<String, Object>> search(
            @Param("status") Boolean status,
            @Param("search") String search
            , Pageable pageable);


//    List<Currency> findByCompanyIdAndIdIn(Long cid, HashSet<Object> ids);
//    Optional<Currency> findByCompanyIdAndCurrencyCodeAndStatus(Long cid, String currencyCode, Integer status);

//    @Query(value = "select ac" +
//            " from book ac" +
//            " where" +
//            " ( ac.status = :status or (coalesce(:status, -1) = -1) )" +
//            " and (lower(concat(coalesce(ac.name, ''), coalesce(ac.author, '')))" +
//            " like (lower(concat('%',:search,'%'))) " +
//            " or (coalesce(:search, '#') = '#')) " )
//    Page<Map<String, Object>> search(
//            @Param("search") String search
//            , Pageable pageable);
//
//
//
//    @Query(value = "select c.id as id" +
//            " , c.currencyCode as currencyCode" +
//            " , c.currencyName as currencyName" +
//            " , c.roundLevel as roundLevel" +
//            " , cc.id as companyId" +
//            " , cc.decimalSeparator as decimalSeparator" +
//            " , cc.thousandSeparator as thousandSeparator" +
//            " from Currency c" +
//            " inner join CompanyCode cc on cc.localCurrencyId = c.id" +
//            " where cc.companyId = :cid"
//    )
//    Optional<Map<String, Object>> getByCid(
//            @Param("cid") Long cid
//    );
}

