package com.supervise.tasksystem.commandline;

import com.supervise.tasksystem.util.VirtualTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CommandLineInput {
    private static Scanner input=new Scanner(System.in);
    public static String nextLine(){
        return input.nextLine();
    }
    public static String nextNLine(int num){
        String inputStr="";
        for(int i=0;i<num;i++){
            inputStr+=input.nextLine()+"\n";
        }
        return inputStr;
    }
    public static int nextPositiveIntOrZero(){
        String outputText="请输入一个非负数。";
        while (true){
            int num;
            try{
                System.out.println(outputText);
                num=input.nextInt();
                if(num>=0){
                    return num;
                }else{
                    throw new InputMismatchException();
                }
            }catch (InputMismatchException e){
                System.out.println("需要一个合法输入.");
            }

        }
    }
    public static int chooseNumber(int[] numsInput){
        int[] nums=Arrays.copyOf(numsInput,numsInput.length);
        Arrays.sort(nums);
        String outputText="";
        outputText+="请从";
        for(int i=0;i<nums.length;i++){
            outputText+=nums[i];
            if(i+1<nums.length){
                outputText+="、";
            }
        }
        outputText+="中选择输入";
        while (true){
            int num;
            try{
                System.out.println(outputText);
                num=input.nextInt();
                if(Arrays.binarySearch(nums,num)>=0){
                    return num;
                }else{
                    throw new InputMismatchException();
                }
            }catch (InputMismatchException e){
                System.out.println("需要一个合法输入.");
            }

        }
    }
    public static Date nextFutureDate(){
        String outputText="请输入日期，格式为“年-月-日”";
        while (true){
            try{
                System.out.println(outputText);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                Date date = simpleDateFormat.parse(input.nextLine());
                if(!VirtualTime.getDate().before(date)){
                    System.out.println("不能早于当前时间.当前时间为:"+VirtualTime.getDate());
                }
                return date;
            }catch (ParseException e){
                System.out.println("需要一个合法输入.");
            }

        }
    }

    public static Date nextDate(){
        String outputText="请输入日期，格式为“年-月-日”";
        while (true){
            try{
                System.out.println(outputText);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                Date date = simpleDateFormat.parse(input.nextLine());
                return date;
            }catch (ParseException e){
                System.out.println("需要一个合法输入.");
            }

        }
    }


    public static int nextInt(){
        String outputText="请输入数字";
        while (true){
            try{
                System.out.println(outputText);
                int num=input.nextInt();
                return num;
            }catch (InputMismatchException e){
                System.out.println("需要一个合法输入.");
            }

        }
    }
}
