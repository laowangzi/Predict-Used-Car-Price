package Try;

import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/*
发现预测结果存在很多负值，显然是不合理的，因此对结果进行处理，把负值用平均值代替
不知此举是否符合规则，有投机取巧之嫌
但我预计此举可减小误差
 */
public class ProcessRes {
    public static void main(String[] args) {
        try {
            File train = new File("E://DataMining-SourceData//Used_Car//111.arff");
            ArffLoader arf1 = new ArffLoader();
            arf1.setFile(train);
            Instances BuildModel = new Instances(arf1.getDataSet());

            BuildModel.setClassIndex(27);
            double mean=0;
            double x=0;
            for(int i=0;i<BuildModel.numInstances();i++)
            {
                if(BuildModel.instance(i).classValue()>0)
                {
                    mean+=BuildModel.instance(i).classValue();
                    x++;
                }
            }
            mean=mean/x;
            for(int i=0;i<BuildModel.numInstances();i++)
            {
                if(BuildModel.instance(i).classValue()<=0)
                {
                    BuildModel.instance(i).setClassValue(mean);
                }
            }
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("E://DataMining-SourceData//Used_Car//TouJi.csv"));
            writer.write(BuildModel.toString());
            writer.newLine();
            writer.flush();
            writer.close();
        }catch (Exception e)
        {
            System.out.println("异常");
        }
    }
}
