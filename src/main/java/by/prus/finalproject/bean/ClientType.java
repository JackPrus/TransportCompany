package by.prus.finalproject.bean;

/**
 * Client type means differense between Person (physicaly), Company (Legal Entity),
 * and Office some other office of compaye can replace order for other office
 * (for example if free trucks of current manager are missing, but was taken by manager
 * he can send this order to other office)
 * @autor Dzmitry Prus
 * @version 1.0
 */
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

    public static ClientType getClientTypeById (int typeID){
        for (ClientType ct : ClientType.values()){
            if (ct.getIdentity()==typeID){
                return ct;
            }
        }
        throw new IllegalArgumentException("Illegal Client type id pointed, please check mistakes");
    }

}

