package kys24.user.utils;

import java.util.Random;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;


public class YUUtils {
    public static String getMessageStatus(String phone){
        String str;
        String url = "http://gw.api.taobao.com/router/rest";
        String appkey = "23542030";
        String secret = "d61caf1bfaa9a64a9ce68f4f2aab5f9a";
        String num = YUUtils.getRandom();
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend( "" );
        req.setSmsType( "normal" );
        req.setSmsFreeSignName( "魏垚" );
        req.setSmsParamString( "{name:'用户',token:'"+num+"'}" );
        req.setRecNum( phone );
        req.setSmsTemplateCode( "SMS_29115081" );
        try {
            AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
            System.out.println(rsp.getBody());
            str = num;
        } catch (Exception e) {
            str = "0";
        }
        return str;
    }

    public static String getRandom(){
        String baseNumLetter = "0123456789";
        String num;
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<5;i++){
            num = baseNumLetter.charAt(new Random().nextInt(baseNumLetter.length()))+"";
            sb.append(num);
        }
        return sb.toString();
    }
}