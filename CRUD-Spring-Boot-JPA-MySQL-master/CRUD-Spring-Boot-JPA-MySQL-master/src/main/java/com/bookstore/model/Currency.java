//package com.bookstore.model;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//import org.apache.tomcat.util.modeler.BaseModelMBean;
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.management.MBeanException;
//import javax.management.RuntimeOperationsException;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//
//
//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@EqualsAndHashCode(callSuper = false)
//public class Currency extends BaseModelMBean {
//    @Id
////    @GenericGenerator(name = "id",strategy = "com.xdp.lib.generator.SnowflakeId")
//    @GeneratedValue(generator = "id")
//    private Long id;
//    @Column(length = 3)
//    private String currencyCode;
//    @Column(length = 20)
//    private String currencyName;
//
//    private Integer roundLevel;
//
////    protected Currency() throws MBeanException, RuntimeOperationsException {
////    }
//
//}
//
