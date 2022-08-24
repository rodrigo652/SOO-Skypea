package com.hamidur.springBootRESTfulWebservices.utils.recomender;

import java.io.FileWriter;
import java.io.Writer;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.hamidur.springBootRESTfulWebservices.models.Evaluation;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

public class BeanToCSV {

    public BeanToCSV(){
    }

    public static void generateCSV(String csv_name, List<RecommenderReviews> recommenderReviews) {
        try {

            // Creating writer class to generate
            // csv file
            FileWriter writer = new
                    FileWriter(csv_name);

            // Create Mapping Strategy to arrange the
            // column name in order
            ColumnPositionMappingStrategy mappingStrategy=
                    new ColumnPositionMappingStrategy();
            mappingStrategy.setType(RecommenderReviews.class);

            // Arrange column name as provided in below array.
            String[] columns = new String[]
                    {"customerId", "flightId", "score"};
            mappingStrategy.setColumnMapping(columns);

            // Creating StatefulBeanToCsv object
            StatefulBeanToCsvBuilder<RecommenderReviews> builder=
                    new StatefulBeanToCsvBuilder(writer);
            StatefulBeanToCsv beanWriter =
                    builder.withMappingStrategy(mappingStrategy).withApplyQuotesToAll(false).build();

            // Write list to StatefulBeanToCsv object
            beanWriter.write(recommenderReviews);

            // closing the writer object
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
