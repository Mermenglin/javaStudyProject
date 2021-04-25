package mml.refleck.entity;

public class Student1{
    private String name;
    private  int age;
    private double score;
    public String   GetScore(String name,double score){
        if(score>500){
            System.out.println("恭喜成功！");
        }
        else {
            System.out.println("继续加油！");
        }
        return  name;
    }
}