package Try;
import java.io.*;
public class DataCleaning {
    public static void main(String[] args) {
        //File dirty=new File("E://DataMining-SourceData//Used_Car//used_car_train_20200313//used_car_train_20200313.csv");
        //File clean=new File("E://DataMining-SourceData//Used_Car//used_car_train_20200313//CleanedData.csv");
        try {
            FileInputStream inputstream = new FileInputStream("E://DataMining-SourceData//Used_Car//used_car_testB_20200421//used_car_testB_20200421.csv");
            BufferedReader read=new BufferedReader(new InputStreamReader(inputstream));
            FileOutputStream output=new FileOutputStream("E://DataMining-SourceData//Used_Car//used_car_testB_20200421//CleanedTrain.csv");
            BufferedWriter write=new BufferedWriter(new OutputStreamWriter(output));

            String temp=null;
            while ((temp=read.readLine())!=null){
                //temp.replace(" ",",");
                write.write(temp.replace(" ",","));
                write.newLine();
            }

            write.close();
            output.close();
            read.close();
            inputstream.close();
        }catch (Exception e)
        {

        }
    }
}
