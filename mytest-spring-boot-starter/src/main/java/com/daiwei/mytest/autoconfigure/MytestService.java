package com.daiwei.mytest.autoconfigure;

public class MytestService {

    private MytestProperties mytestProperties;

    public MytestService() {
    }

    public void setMytestProperties(MytestProperties mytestProperties) {
        this.mytestProperties = mytestProperties;
    }

    public String getName(String name) {
        return mytestProperties.getPersonPropertiesMap().get(name).getName();
    }

    public int getAge(String name) {
        return mytestProperties.getPersonPropertiesMap().get(name).getAge();
    }
}
