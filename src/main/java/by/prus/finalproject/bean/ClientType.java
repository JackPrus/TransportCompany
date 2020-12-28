package by.prus.finalproject.bean;

public enum ClientType {

    PERSON(1),
    COMPANY(2),
    OFFICE(3);

    int clientTypeId;

    private ClientType(int clientTypeId){ this.clientTypeId=clientTypeId; }
    public Integer getIdentity() {
            return clientTypeId;
        }
    public static ClientType getByIdentity(Integer identity) {
        return ClientType.values()[identity-1];
    }

    public static ClientType ct (int typeID){
        for (ClientType ct : ClientType.values()){
            if (ct.getIdentity().equals(typeID)){
                return ct;
            }
        }
        throw new IllegalArgumentException("Illegal Client type id pointed, please check mistakes");
    }

}

