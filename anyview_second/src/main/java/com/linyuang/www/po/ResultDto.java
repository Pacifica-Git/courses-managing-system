package com.linyuang.www.po;

import java.util.HashMap;

import static com.linyuang.www.config.Constant.*;

public class ResultDto extends HashMap<String,String> {
    public ResultDto(Integer code,String msg,String data) {
        super.put(CODE,code.toString());
        super.put(MSG,msg);
        super.put(DATA,data);
    }

    public ResultDto(String msg,String data) {
        super.put(MSG,msg);
        super.put(DATA,data);
    }
    public ResultDto(String msg) {
        super.put(MSG,msg);
    }
}
