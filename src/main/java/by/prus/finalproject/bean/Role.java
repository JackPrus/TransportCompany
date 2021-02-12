package by.prus.finalproject.bean;

/**
 * There are 3 roles. Manager, Client, Driver.
 * Client can create orders and send it to CRM.
 * Manager can take orders, set truck for them, send truck to carriage and close order
 * when it was delivered.
 * Driver can see list of his orders he is responsible for.
 * And can take them for carriage at warehouse.
 * @autor Dzmitry Prus
 * @version 1.0
 */
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
