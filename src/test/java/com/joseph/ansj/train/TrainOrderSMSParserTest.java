package com.joseph.ansj.train;

import org.junit.Assert;
import org.junit.Test;

public class TrainOrderSMSParserTest {

    @Test
    public void testParse() {
        String sms1 = "【铁路客服】订单EF91817420,董约瑟您已购1月15日6074次2车39号,平凉站13:10开。";
        String sms2 = "【铁路客服】订单EF62052112,董约瑟您已购2月12日G7581次10车4D号,昆山南站08:50开,检票口：3。";
        String sms3 = "【铁路客服】订单EF62052112,董约瑟您已购2月12日G7581次10车4D号、4F号,哈尔滨南站08:50开,检票口：3。";

        TrainInfo info1 = TrainOrderSMSParser.parse(sms1);
        System.out.println(info1);
        Assert.assertTrue("平凉站".equals(info1.getStation()));
        Assert.assertTrue("39号".equals(info1.getSeatNumber()));

        TrainInfo info2 = TrainOrderSMSParser.parse(sms2);
        System.out.println(info2);
        Assert.assertTrue("昆山南站".equals(info2.getStation()));
        Assert.assertTrue("4D号".equals(info2.getSeatNumber().toUpperCase()));

        TrainInfo info3 = TrainOrderSMSParser.parse(sms3);
        System.out.println(info3);
        Assert.assertTrue("4D号、4F号".equals(info3.getSeatNumber().toUpperCase()));
    }
}
