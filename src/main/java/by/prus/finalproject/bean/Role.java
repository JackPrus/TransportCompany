package by.prus.finalproject.bean;

public enum Role {

    MANAGER("MANAGER"),
    CLIENT("CLIENT"),
    DRIVER("DRIVER");

    String roleName;

    private Role(String clientTypeId){ this.roleName=clientTypeId; }

    public Integer getIdentity() {
        return ordinal();
    }
    public static Role getByIdentity(Integer identity) {
        return Role.values()[identity];
    }

    public String getDescription(Role role){return role.roleName;}

    public String getRoleName(){ return this.roleName; }

    public static Role getRole (String roleName){
        for (Role role : Role.values()){
            if (role.getDescription(role).equals(roleName)){
                return role;
            }
        }
        throw new IllegalArgumentException("Illegal city name pointed, please check mistakes");
    }

}
