package com.children.game.testwork.Singleton;

public class SingletonStartHomeActivity {
    private static SingletonStartHomeActivity instance;

    private SingletonStartHomeActivity (){
    }

    public static SingletonStartHomeActivity getInstance(){
        if (null == instance){
            instance = new SingletonStartHomeActivity();
        }
        return instance;
    }
}