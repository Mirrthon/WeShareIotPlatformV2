package redis;


import flink.dao.BoolTypeRule;
import flink.dao.DigitTypeRule;
import flink.dao.VariableRule;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lr
 * @Package redis
 * @date 2020/4/7 11:08
 * @description  Redis测试
 */
public class RedisTest {


    /**
     Redis当前 key="1001"中存了两条规则，分别是：
     id =1  value= VariableRule(variableFlag=0, boolTypeRule=BoolTypeRule(triggerCondition=1), digitTypeRule=null, attribute=kaiguan)
     id= 2  value= VariableRule(variableFlag=1, boolTypeRule=null, digitTypeRule=DigitTypeRule(START_SECTION=1.0, END_SECTION=5.0, triggerCondition=2), attribute=temperature)
     */

    /**
     * Redis当前 key="DEVICE-ID"中存了两条规则，分别是：
     * id=1, value =VariableRule(variableFlag=1, boolTypeRule=null, digitTypeRule=DigitTypeRule(START_SECTION=30.0, END_SECTION=40.0, triggerCondition=2), attribute=temperature)
     * id=2, value =VariableRule(variableFlag=0, boolTypeRule=BoolTypeRule(triggerCondition=1), digitTypeRule=null, attribute=switch)
     */
    public static void main(String[] args) {

        //添加booltype规则
//        VariableRule variableRule = new VariableRule();
//        BoolTypeRule boolTypeRule = new BoolTypeRule();
//        boolTypeRule.setTriggerCondition(1);
//        variableRule.setVariableFlag(0);
//        variableRule.setAttribute("switch");
//        variableRule.setBoolTypeRule(boolTypeRule);


//       添加digitype规则
//        VariableRule variableRule = new VariableRule();
//        DigitTypeRule digitTypeRule = new DigitTypeRule();
//        digitTypeRule.setSTART_SECTION(30);
//        digitTypeRule.setEND_SECTION(40);
//        digitTypeRule.setTriggerCondition(2);
//        variableRule.setVariableFlag(1);
//        variableRule.setAttribute("temperature");
//        variableRule.setDigitTypeRule(digitTypeRule);


        //插入单条hash数据
//        RedisOps.setObjectHash("DEVICE-ID",2,variableRule);


        //查询单条hash数据
//        VariableRule variableRule1 =(VariableRule) RedisOps.getObjectHash("DEVICE-ID", 2);
//        System.out.println(variableRule1);

        //批量查询hash中的数据并打印
//        HashMap<Integer, VariableRule> map = RedisOps.getObjectHashAll("DEVICE-ID");
//        if (map.size() != 0) {
//            for (HashMap.Entry<Integer, VariableRule> entry : map.entrySet()) {
//                VariableRule rule = entry.getValue();
//                System.out.println(rule);
//            }
//        }




//        RedisOps.setObject("test:4", variableRule);//序列化
//        VariableRule variableRule1 = (VariableRule) RedisOps.getObject("test:4");//反序列化

//        System.out.println(variableRule1);
//        System.out.println(variableRule1.getDeviceID());
// }
    }
}
