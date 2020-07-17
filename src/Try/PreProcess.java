package Try;

import weka.core.Attribute;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class PreProcess {
    public static void main(String[] args) {
        try {
            //导入训练集和要预测的数据
            File train = new File("E://DataMining-SourceData//Used_Car//used_car_train_20200313//CleanedDataArff.arff");
            ArffLoader arf1 = new ArffLoader();
            arf1.setFile(train);
            Instances BuildModel = new Instances(arf1.getDataSet());

            File test = new File("E://DataMining-SourceData//Used_Car//used_car_testB_20200421//CleanedTrain_1.arff");
            ArffLoader arf2 = new ArffLoader();
            arf2.setFile(test);
            Instances predict = new Instances(arf2.getDataSet());

            //填充缺失属性
            Instances HaveAtrribute=new Instances(predict);
          //  Attribute Damage=new Attribute("notRepairedDamage");
            for(int i=0;i<HaveAtrribute.numInstances();i++)
            {
                if(HaveAtrribute.instance(i).hasMissingValue())
                {
                    HaveAtrribute.instance(i).setValue(11,"0");
                }
            }

            for(int i=0;i<BuildModel.numInstances();i++)   //总结weka处理缺失值的方法
            {
                if(BuildModel.instance(i).hasMissingValue())
                {
                    BuildModel.delete(i);
                }
            }

            //save DataSet
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("E://DataMining-SourceData//Used_Car//LastTry//Train.arff"));
            writer.write(HaveAtrribute.toString());
            writer.newLine();
            writer.flush();
            writer.close();

            BufferedWriter writer2 = new BufferedWriter(
                    new FileWriter("E://DataMining-SourceData//Used_Car//LastTry//Test.arff"));
            writer2.write(BuildModel.toString());
            writer2.newLine();
            writer2.flush();
            writer2.close();
        }
        catch (Exception e)
        {
              System.out.println("异常");
        }
    }
}
