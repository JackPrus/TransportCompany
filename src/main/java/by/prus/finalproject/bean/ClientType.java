package by.prus.finalproject.bean;

public enum ClientType {

    PERSON(1),
    COMPANY(2),
    OFFICE(3);

    int clientTypeId;

    private ClientType(int clientTypeId){ this.clientTypeId=clientTypeId; }
    public Integer getIdentity() {
            return ordinal();
        }
    public static ClientType getByIdentity(Integer identity) {
        return ClientType.values()[identity];
    }


}

