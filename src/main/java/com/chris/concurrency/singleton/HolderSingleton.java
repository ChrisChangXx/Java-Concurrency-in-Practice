package com.chris.concurrency.singleton;

//应用比较广泛的一种单例模式（Holder方式）
public class HolderSingleton {
    private HolderSingleton(){

    }

    private static class Holder{
        private static HolderSingleton instance = new HolderSingleton();
    }
    //懒加载
    //没有使用synchronized，效率更高
    public static HolderSingleton getInstance(){
        return Holder.instance;
    }
}
