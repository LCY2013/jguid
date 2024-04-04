package org.fufeng.design.principle.singleresponsibility;

/**
 * 单一职责原则
 *
 * @author fufeng
 * {@code @Date} 2024-04-04 17:09
 */
public class UserMethod {

    //v1
    private void updateUserInfo(String userName, String address) {
            userName = "fufeng";
            address = "chengdu";
    }

    private void updateUserInfo(String userName, String... address) {
        userName = "fufeng";
        address = new String[]{"chengdu", "beijing"};
    }

    //v2
    private void updateUserName(String userName) {
        userName = "fufeng";
    }

    private void updateAddress(String address) {
        address = "chengdu";
    }

    //v3
    private void updateUserinfo(String userName, String address, boolean bool) {
        if (bool) {
            // do something1
        } else {
            // do something2
        }

        userName = "fufeng";
        address = "chengdu";
    }

    private void dosomthing1() {
        // do something1
    }

    private void dosomthing2() {
        // do something1
    }

    private void updateUserName3(String userName) {
        userName = "fufeng";
    }

    private void updateAddress3(String address) {
        address = "chengdu";
    }

}
