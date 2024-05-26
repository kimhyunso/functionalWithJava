package org.example.domain;

import java.util.function.Function;

public class SubClass extends SuperClass{

    @Override
    public String doWork(String input){
        return "this: " + input;
    }

    public String thisWork(String input){
        Function<String, String> thisWorker = this::doWork;
        return thisWorker.apply(input);
    }

    public String superWork(String input){
        Function<String, String> superWorker = super::doWork;
        return superWorker.apply(input);
    }

}
