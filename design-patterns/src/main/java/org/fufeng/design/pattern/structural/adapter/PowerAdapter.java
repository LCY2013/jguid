package org.fufeng.design.pattern.structural.adapter;

/**
 *
 * @author fufeng
 * {@code @Date} 2024-04-05 19:52
 */
public class PowerAdapter implements DC5 {

    private final AC220 ac220 = new AC220();

    @Override
    public int outputDC5V() {
        // 变压器
        int adapterInput = ac220.outputAC220V();
        int adapterOutput = adapterInput / 44;
        System.out.println("使用PowerAdapter输入AC:" + adapterInput + "V" + " 输出DC:" + adapterOutput + "V");
        return adapterOutput;
    }
}
