package by.prus.finalproject.domain;

public enum Role {

    MANAGER(1),
    CLIENT(2),
    DRIVER(3);

    int clientTypeId;

    private Role(int clientTypeId){ this.clientTypeId=clientTypeId; }
    public Integer getIdentity() {
        return ordinal();
    }
    public static Role getByIdentity(Integer identity) {
        return Role.values()[identity];
    }

}
