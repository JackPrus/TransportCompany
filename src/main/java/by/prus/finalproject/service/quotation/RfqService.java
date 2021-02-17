package by.prus.finalproject.service.quotation;

import by.prus.finalproject.bean.RequestForQuotation;
import by.prus.finalproject.exception.ServiceException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.Provider;

public class RfqService {

    // formula PRICE : 770* (Длина/Макс_длина)*(Ширина/Макс_ширина)*(Вес/Макс_вес)*к
    //к зависиот от занимаемого места в машине.
    // То есть если будет вся машина занята 1-м клиентом, к будет равен 1.
    // Если груз меньше - коэффициент наценки будет выше.
    // площадь >70% - к = 1,1
    // >0,5<=0,7 ; k = 1,5
    // >0,3<=0,5 ; k = 1,9
    // >0,2<=0,3 ; k = 2,2
    // >0,1>=0,2 ; k = 2,5
    // <0,1      ; k = 2,8;

    private final double defaultPrice = 770; // установленная ставка для перевозок по территории РБ
    private double k;                       // повышающий коэффициент для мелких отправок
    private final int maxLength = 1300;     // длина кухова транспортного средства cm
    private final int maxWidth = 245;       // ширина кузова транспортного средства cm
    private final int maxHeight = 275;      // высота кузова транспортного средства cm
    private final int maxWeight = 23000;    // максиальная грузоподъемность транспортного средства kg

    private final double K1 = 1.5;
    private final double K2 = 2.7;
    private final double K3 = 3.2;
    private final double K4 = 4.5;
    private final double K5 = 35.9;

    public BigDecimal countPrice(RequestForQuotation rfq) throws ServiceException {

//        k = rfq.getLength() /
        if (checkAllData(rfq)){
            k = countK(rfq);
        }else {
            throw new ServiceException("Something wrong in data, check if params of cargo is real");
        }

        BigDecimal stavka = BigDecimal.valueOf(defaultPrice);
        BigDecimal lengthK = BigDecimal.valueOf((double) rfq.getLength()/maxLength);
        BigDecimal widthK = BigDecimal.valueOf((double) rfq.getWidth()/maxWidth);
        BigDecimal weightK = BigDecimal.valueOf(rfq.getWeight()/maxWeight);
        BigDecimal localK = BigDecimal.valueOf(this.k);

        BigDecimal price = stavka.multiply(lengthK).multiply(widthK).multiply(weightK).multiply(localK);
        BigDecimal scaledPrice = price.setScale(2, RoundingMode.HALF_UP);

        return scaledPrice;

    }

    private boolean checkAllData(RequestForQuotation rfq) {
        if (
                        (rfq.getLength() <= maxLength && rfq.getLength()>0) &&
                        (rfq.getWidth() <= maxWidth && rfq.getWidth()>0) &&
                        (rfq.getHeight() <= maxHeight && rfq.getHeight()>0) &&
                        (rfq.getWeight() <= maxWeight && rfq.getWeight()>0)
        ) {
            return true;
        } else {
            return false;
        }
    }

    private double countK (RequestForQuotation rfq){

        // f_площадь = ((Длина * Ширина) / (Макс_длина * Макс_ширина))
        // f_вес = (Вес / Макс_вес)

        double f_weight = rfq.getWeight()/maxWeight;
        double f_space = ((double) rfq.getLength()*rfq.getWidth())/((double) maxLength*maxWidth);

        //коэффициент вычисления заполняемости пространства в фуре
        double k_space=-1;

        if (f_space>0.7){ k_space = 1.1;
        }else if (f_space>0.5&&f_space<=0.7){ k_space = K1;
        }else if (f_space>0.3&&f_space<=0.5){ k_space = K2;
        }else if (f_space>0.2&&f_space<=0.3){ k_space = K3;
        }else if (f_space>0.1&&f_space<=0.2){ k_space = K4;
        }else if (f_space<=0.1){ k_space = K5;
        }

        //коэффициент использования грузоподъемности транспортного средства
        double k_weight=-1;

        if (f_weight>0.7){ k_weight = 1.1;
        }else if (f_weight>0.5&&f_weight<=0.7){ k_weight = K1;
        }else if (f_weight>0.3&&f_weight<=0.5){ k_weight = K2;
        }else if (f_weight>0.2&&f_weight<=0.3){ k_weight = K3;
        }else if (f_weight>0.1&&f_weight<=0.2){ k_weight = K4;
        }else if (f_weight<=0.1){ k_weight = K5;
        }

        //используется понижение коэффициента, если большая заполняемость от одного заказчика
        return (k_weight<2||k_space<2) ? k_weight+k_space-1 : k_weight+k_space;
    }

}
