package Try;

import weka.classifiers.Classifier;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;


/*
本实验的最终版本
1、采用原生数据集，从特征工程开始走一套完整流程
2、缺失值大概占15%，尝试删去这15%的数据训练。预测数据集采用0值填充法
3、删除SaleID、name这两个属性，把DamagenotRepair属性类型填充后修改为numeric数据类型
 */

public class Try_3 {

    public static void main(String[] args) {
        try
        {
            //导入训练集和要预测的数据
            File train = new File("E://DataMining-SourceData//Used_Car//used_car_train_20200313//CleanedDataArff.arff");
            ArffLoader arf1 = new ArffLoader();
            arf1.setFile(train);
            Instances BuildModel = new Instances(arf1.getDataSet());

            File test = new File("E://DataMining-SourceData//Used_Car//used_car_testB_20200421//CleanedTrain_1.arff");
            ArffLoader arf2 = new ArffLoader();
            arf2.setFile(test);
            Instances predict = new Instances(arf2.getDataSet());


            //训练
            predict.setClassIndex(predict.numAttributes()-1);
            predict.setClassIndex(predict.numAttributes()-1);
            Classifier classifier=new MultilayerPerceptron();
            classifier.buildClassifier(predict);
            //进行预测
            for(int i=0;i<predict.numInstances();i++)
            {
                double price=classifier.classifyInstance(predict.instance(i));
                predict.instance(i).setClassValue(price);
                System.out.println(predict.instance(i));
            }

// save labeled data
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("E://DataMining-SourceData//Used_Car//R_2.arff"));
            writer.write(predict.toString());
            writer.newLine();
            writer.flush();
            writer.close();


        }
        catch(Exception e){
            System.out.println("异常");
        }
    }
}
