package flink.map;


import com.alibaba.fastjson.JSONObject;
import flink.dao.VariableRule;
import netty.deviceMessage.DeviceMessage;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.configuration.Configuration;
import redis.RedisConnection;
import redis.RedisOps;

import javax.persistence.Tuple;
import java.util.HashMap;


/**
 * @ClassName AlarmMap
 * @Description TODO 告警map
 * @Author tuantuan
 * @Date 2020/4/10 上午11:25
 * @Version 1.0
 * @Attention Copyright (C), 2004-2019, BDILab, XiDian University
 **/
public class AlarmMap extends RichMapFunction<String, Tuple3<String,String,String>> {
    //RedisConnection里redis链接最好单例模式，节省资源
    @Override
    public void open(Configuration parameters) throws Exception {
        //获取redis链接
        super.open(parameters);
        RedisConnection.getJedis();
    }

    @Override
    public Tuple3<String,String,String> map(String s) throws Exception {
        //Redis当前 key="1001"中存了两条规则，分别是：
//        VariableRule(variableFlag=0, boolTypeRule=BoolTypeRule(triggerCondition=1), digitTypeRule=null, attribute=kaiguan)
//        VariableRule(variableFlag=1, boolTypeRule=null, digitTypeRule=DigitTypeRule(START_SECTION=1.0, END_SECTION=5.0, triggerCondition=2), attribute=temperature)
//        从kafka中拿数据，主要拿取设备id
//        kaiguan  和 temperature 中的数据 为 String ,如果想为Int ,需要改相应的规则判断BoolTypeRule和DigitTypeRule
        String line = s;
//        System.out.println(line);
        DeviceMessage deviceMessage = new DeviceMessage();
        JSONObject jsonObject = new JSONObject().parseObject(line);
        deviceMessage.setPRODUCT_ID(jsonObject.getString("PRODUCT_ID"));
        deviceMessage.setDEVICE_ID(jsonObject.getString("DEVICE_ID"));
        deviceMessage.setFormatData(jsonObject.getJSONObject("formatData"));
        deviceMessage.setMETA_DATA(jsonObject.getBytes("META_DATA"));
        String alarmvalue = "不告警";
//        System.out.println(deviceMessage);
        HashMap<Integer, VariableRule> map = RedisOps.getObjectHashAll(jsonObject.getString("DEVICE_ID"));
        if (map.size() != 0) {
            int i = 0;
            for (HashMap.Entry<Integer, VariableRule> entry : map.entrySet()) {
                VariableRule rule = entry.getValue();
                boolean flag = rule.Judge(deviceMessage);
                //如果flag是true，说明满足告警条件，需要向web端发送一个http告警请求，提交给线程池异步处理
                if (flag == true) {
                    i++;
                } else {
                    System.out.println("不符合报警规则");
                    break;
                }
            }
            if (i == map.size()) {
                System.out.println("符合报警规则发出告警！需要向web端发送一个http告警请求，提交给线程池异步处理");
                alarmvalue = "告警";
            }
        }
        else{
            System.out.println("无该设备相应的规则");

        }
        return Tuple3.of(s, alarmvalue,"推送");
    }

    @Override
    public void close() throws Exception {
        super.close();
        RedisConnection.getJedis().close();
    }
}
