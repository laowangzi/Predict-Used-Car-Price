package Try;

import java.io.*;
import java.util.logging.LogRecord;

//import sun.security.jca.GetInstance;
import weka.classifiers.Classifier;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.filters.Filter;
import weka.filters.Filter.*;
import weka.filters.unsupervised.attribute.Remove;

import javax.activation.DataSource;


/*
使用线性回归预测
正式提交的第一版
未使用DamageRepaire属性
 */


public class Try_2 {
    public static void main(String[] args) {
        Classifier classifier=new LinearRegression();
        try{
            //导入训练集和要预测的数据
            File train=new File("E://DataMining-SourceData//Used_Car//used_car_train_20200313//CleanedDataArff.arff");
            ArffLoader arf1=new ArffLoader();
            arf1.setFile(train);
            Instances BuildModel=new Instances(arf1.getDataSet());

            File test=new File("E://DataMining-SourceData//Used_Car//used_car_testB_20200421//CleanedTrain_2Arff.arff");
            ArffLoader arf2=new ArffLoader();
            arf2.setFile(test);
            Instances predict=new Instances(arf2.getDataSet());

            //过滤器，转换数据格式
            String[] options = new String[2];
            options[0] = "-R";                             // "range"
            options[1] = "11";                              // first attribute是第几个就写第几
            Remove remove = new Remove();                 // new instance of filter
            remove.setOptions(options);                  // set options
            remove.setInputFormat(BuildModel);
            remove.setInputFormat(BuildModel);
            Instances newData = Filter.useFilter(BuildModel,remove);

            //指定目标属性
            newData.setClassIndex(newData.numAttributes()-1);  //30 属性从0开始计数
            predict.setClassIndex(predict.numAttributes()-1);

            //训练模型
            classifier.buildClassifier(newData);

            //进行预测
            for(int i=0;i<predict.numInstances();i++)
            {
                double price=classifier.classifyInstance(predict.instance(i));
                predict.instance(i).setClassValue(price);
                System.out.println(predict.instance(i));
            }

// save labeled data
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("E://DataMining-SourceData//Used_Car//R.arff"));
            writer.write(predict.toString());
            writer.newLine();
            writer.flush();
            writer.close();


        }catch (Exception e)
        {
            System.out.println("异常");
        }
    }
}
