package by.prus.finalproject.bean;

public enum City {

    BREST("Brest"), //1
    COBRIN("Cobrin"), //2
    PINSK("Pinsk"), //3
    BARANOVICHY("Baranovichy"), //4
    IVATSEVICHY("Ivatsevichy"), //5
    LUNINETS("Luninets"), //6
    PRUZHANY("Pruzhany"), //7

    HRODNA("Hrodna"), //8
    VOLKOVYSK("Volkovysk"),  //9
    SLONIM("Slonim"), //10
    NOVOHRUDOK("Novohrudok"), //11
    LIDA("Lida"), //12
    OSHMYANY("Oshmyany"), //13

    VITEBSK("Vitebsk"), //14
    GLUBOKOE("Glubokoe"), //15
    LEPEL("Lepel"), //16
    POLOTSK("Polotsk"), //17
    ORSHA("Orsha"),  //18

    MAHILYOW ("Mahilyov"),  //19
    GORKY("Gorky"), //20
    MSTISLAVL("Mstislavl"), //21
    KRYCHEV("Krychev"), //22
    BOBRUISK("Bobruisk"), //23
    OSIPOVICHY("Osipovichy"),  //24

    HOMEL("Homel"),  //25
    MAZYR("Mazyr"), //26
    ZHLOBIN("Zhlobin"), //27
    RECHITSA("Rechitsa"), //28
    SVETLAHORSK("Svetlahorsk"),  //29
    PETRYKAU("Petrykau"), //30
    LELCHITSY ("Lelchitsy"), //31
    ZHITKOVICHY("Zhitkovichy"), //32

    MINSK("Minsk"), //33
    SALIHORSK("Salihorsk"), //34
    SLUTSK("Slutsk"), //35
    MARYNAHORKA ("Maryna Horka"), //36
    STOLBTSY("Stolbtsy"),  //37
    DZERCHINSK("Dzerchinsk"), //38
    MOLODZECHNO("Molodzechno"), //39
    MYADEL("Myadel"), //40
    ZHODINO("Zhodino"), //41
    BORISOV("Borisov"), //42
    BEREZINO("Berezino"); //43

    String cityName;

    private City(String cityName){ this.cityName=cityName; }

    public static City getByIdentity(Integer identity) {
        return City.values()[identity];
    }
    public String getCityName() {
        return cityName;
    }

    public static City getCity (String cityName){
        for (City city : City.values()){
            if (city.getCityName().equals(cityName)){
                return city;
            }
        }
        throw new IllegalArgumentException("Illegal city name pointed, please check mistakes");
    }

}
