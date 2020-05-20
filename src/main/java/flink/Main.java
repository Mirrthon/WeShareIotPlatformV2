package flink;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import netty.deviceMessage.DeviceMessage;
import netty.util.DeviceMessageJson;

import java.io.IOException;

/**
 * @ClassName Main
 * @Description TODO
 * @Author tuantuan
 * @Date 2020/4/5 下午5:05
 * @Version 1.0
 * @Attention Copyright (C), 2004-2019, BDILab, XiDian University
 **/
public class Main {
    public static void main(String[] args) throws IOException {
        DeviceMessage deviceMessage = new DeviceMessage();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("wendu",null);
        deviceMessage.setFormatData(jsonObject);

        System.out.print(deviceMessage.getFormatData().get("wendu"));

    }
}
