package com.joseph.ansj.train;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.library.DicLibrary;
import org.ansj.recognition.impl.StopRecognition;
import org.ansj.splitWord.analysis.DicAnalysis;
import org.ansj.splitWord.analysis.NlpAnalysis;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TrainOrderSMSParser {

    public static void main(String[] args) {
//        String sms = "【铁路客服】订单EF91817420,董约瑟您已购1月15日G6074次2车39号,平凉站13:10开。
        String sms = "【铁路客服】订单EF62052112,董约瑟您已购2月12日G7581次10车4D号、4F号,昆山南站08:50开,检票口：3。";
        DicLibrary.insert(DicLibrary.DEFAULT, "【铁路客服】", "a", 1);
        DicLibrary.insert(DicLibrary.DEFAULT, "订单", "b", 1);
        DicLibrary.insert(DicLibrary.DEFAULT, "您已购", "c", 1);

        StopRecognition filter = new StopRecognition();
        filter.insertStopWords("，");
        filter.insertStopWords("。");

        Result result = DicAnalysis.parse(sms).recognition(filter);
        TrainInfo trainInfo = new TrainInfo();

        List<Term> termList = result.getTerms();
        Term term;
        String date = "";
        for(int i = 0; i < termList.size(); i++) {
            term = termList.get(i);
            if("订单".equals(term.getName())) {
                String orderSn = termList.get(i + 1).getName() + termList.get(i + 2).getName();
                trainInfo.setOrderSn(orderSn);
            } else if("您已购".equals(term.getName())) {
                String name = "";
                int index = i - 1;
                Term temp = termList.get(index);
                while (!temp.getName().equals(",")) {
                    name = temp.getName() + name;
                    index --;
                    temp = termList.get(index);
                }
                trainInfo.setName(name);
            } else if(term.getName().indexOf("月") > 0){
                date = term.getName();
            } else if(term.getName().indexOf("日") > 0) {
                date = date + term.getName();
            } else if(term.getName().indexOf("次") > 0) {
                String trainNumber = "";
                Term lastOneTerm = termList.get(i - 1);
                if("en".equals(lastOneTerm.getNatureStr())) {
                    trainNumber = lastOneTerm.getName();
                }
                trainInfo.setTrainNumber(trainNumber + term.getName());
            } else if(term.getName().indexOf("车") > 0) {
                trainInfo.setWagonBox(term.getName());
            } else if(term.getName().indexOf("号") > 0) {
                trainInfo.setSeatNumber(term.getName());
            } else if(term.getName().indexOf("站") >= 0) {
                trainInfo.setStation(termList.get(i - 1).getName() + term.getName());
            } else if(term.getName().indexOf("开") > 0) {
                trainInfo.setTime(termList.get(i - 2).getName() + ":" + term.getName());
            }
        }
        trainInfo.setDate(date);
        System.out.println(result);
        System.out.println(trainInfo);
    }
}