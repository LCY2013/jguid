package org.fufeng.project.lamda.module04;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @ClassName: JavaScriptEngineModel
 * @author: LuoChunYun
 * @Date: 2019/4/29 10:04
 * @Description: TODO
 */
public class JavaScriptEngineModel {

    public static void main(String[] args) {

        //001 nashorn
        showNashorn();

    }

    private static void showNashorn() {
        final ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        final ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");
        try {
            final Object eval = nashorn.eval("'Hello , World'.length");
            System.out.println("计算字符串的长度:"+eval);
        } catch (ScriptException e) {
            e.printStackTrace();
        }

    }
}
