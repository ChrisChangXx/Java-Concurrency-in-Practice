package com.chris.concurrency.singleton;

//应用比较广泛（枚举方式）更加优雅
public class EnumSingleton {
    private EnumSingleton() {

    }

    //延时加载（懒加载）
    private enum EnumHolder {
        INSTANCE;
        private EnumSingleton instance;

        EnumHolder() {
            this.instance = new EnumSingleton();
        }

        private EnumSingleton getInstance() {
            return instance;
        }
    }

    public static EnumSingleton getInstance() {
        return EnumHolder.INSTANCE.getInstance();
    }
}
