package com.bookstore.api;


import weka.associations.Apriori;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class AprioriAlgorithmExample {
    public static void main(String[] args) {
        try {
            // Load tập tin dữ liệu
            DataSource source = new DataSource("C:/Users/Admin/Desktop/final_data.arff"); // Thay thế bằng đường dẫn đến tập tin dữ liệu của bạn
            Instances data = source.getDataSet();

            System.out.println(data);

            // Khởi tạo mô hình Apriori
            Apriori apriori = new Apriori();
            apriori.setMinMetric(0.5);
            apriori.setLowerBoundMinSupport(0.1);
            apriori.buildAssociations(data);

            // In ra các luật kết hợp được khai phá
            System.out.println(apriori);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}