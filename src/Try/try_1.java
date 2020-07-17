package Try;

import java.io.*;
import java.util.logging.LogRecord;

//import sun.security.jca.GetInstance;
import weka.classifiers.Classifier;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.attribute.StringToNominal;
import weka.filters.Filter.*;

import javax.activation.DataSource;


/*
首次尝试weka，未产出结果
 */


public class try_1 {
    public static void main(String[] args) {
        Classifier classifier = new MultilayerPerceptron();
        try{


           //导入数据集
            // Instances instance = new Instances(new BufferedReader(new FileReader("E://
            File inputFile = new File("E://DataMining-SourceData//Used_Car//used_car_train_20200313//CleanedData.csv");
            CSVLoader csvloader=new CSVLoader();
            csvloader.setFile(inputFile);
            Instances unlabeled=new Instances(csvloader.getDataSet());

            File Answer=new File("E://DataMining-SourceData//Used_Car//used_car_testB_20200421//CleanedTrain.csv");
            CSVLoader csvloader2=new CSVLoader();
            csvloader2.setFile(Answer);
            Instances predict=new Instances(csvloader2.getDataSet());

        //    Instances labeled = new Instances(unlabeled);
            //过滤器，转换数据格式
            String[] options = new String[2];
            options[0] = "-R";                             // "range"
            options[1] = "11";                              // first attribute
            Remove remove = new Remove();                 // new instance of filter
            remove.setOptions(options);                  // set options
            remove.setInputFormat(unlabeled);
            remove.setInputFormat(unlabeled);
            Instances newData = Filter.useFilter(unlabeled,remove);

            //设置要预测的属性
            predict.setClassIndex(31);
            newData.setClassIndex(15);
         //   labeled.setClassIndex(15);
            classifier.buildClassifier(newData);
            for(int i=0;i<unlabeled.numInstances();i++)
            {
                Double pre=classifier.classifyInstance(predict.instance(i));
                predict.instance(i).setClassValue(pre);
            }
         // save labeled data
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("E://DataMining-SourceData//Used_Car//labeled.csv"));
            writer.write(predict.toString());
            writer.newLine();
            writer.flush();
            writer.close();

      /*      while ((temp=read.readLine())!=null){
                //temp.replace(" ",",");
                write.write(temp.replace(" ",","));
                write.newLine();
            }   */

        }
        catch (Exception e){
          System.out.println("false");
        }
    }
}
