package org.nmj.model;

public enum UserType {
    CUSTOMER (1, "Customer"),
    ADMIN(2, "Admin");

    private final int id;
    private final String name;

    UserType(int id, String name){
        this.id = id;
        this.name=name;
    }

    public static UserType fromId(int id){
        for(UserType userType: UserType.values()){
            if(userType.getId()==id){
                return userType;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
