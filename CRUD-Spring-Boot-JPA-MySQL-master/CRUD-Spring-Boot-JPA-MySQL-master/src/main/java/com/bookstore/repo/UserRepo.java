package com.bookstore.repo;

import com.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long>, PagingAndSortingRepository<User, Long> {
    Optional<User> findById(Long id);

//    List<User> findByStatus(boolean status, Pageable pageable);

    Optional<User> findByEmailAndPassword(
            @Param("email") String email
            , @Param("password") String password
    );

//    @Query(value = "select ac.id as id," +
//            " ac.username as username," +
//            " ac.password as password," +
//            " ac.email as email," +
//            " ac.fullname as fullname," +
//            " ac.address as address," +
//            " ac.phone as phone," +
//            " ac.roleId  as roleId ," +
//            " ac.createdAt as createdAt," +
//            " ac.updatedAt as updatedAt" +
//            " from Users as ac" +
//            " where" +
////            " ac.status = :status" +
//            " ac.username = :username " +
//            " and ac.password = :password "
//    )
//    Optional<User> getByUsernamePassword(
////            @Param("status") Boolean status,
//            @Param("username") String username,
//            @Param("password") String password
//            );

//    @Query(value = "select ac.id as id," +
//            " ac.name as name," +
//            " ac.description as description," +
//            " ac.price as price," +
//            " ac.image as image," +
//            " ac.categoryId as categoryId," +
//            " ac.createdAt as createdAt," +
//            " ac.updatedAt as updatedAt," +
//            " ac.status as status," +
//            " ac.discount as discount" +
//            " from Product as ac" +
//            " where" +
//            " ac.status = :status" +
//            " and (lower(concat(coalesce(ac.name, '')))" +
//            " like (lower(concat('%',:search,'%'))) " +
//            " or (coalesce(:search, '#') = '#')) " )
//    Page<Map<String, Object>> search(
//            @Param("status") Boolean status,
//            @Param("search") String search
//            , Pageable pageable);
}

