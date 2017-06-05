package client;

// нужно, чтобы получить wsdl описание и через него
// дотянуться до самого веб-сервиса
import java.net.URL;
// такой эксепшн возникнет при работе с объектом URL
import java.net.MalformedURLException;

// классы, чтобы пропарсить xml-ку c wsdl описанием
// и дотянуться до тега service в нем
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

// интерфейс нашего веб-сервиса (нам больше и нужно)
import gpb.WebService1;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.util.EventListener;


public class WebServiceClient{

//    public static void main(String[] args)throws MalformedURLException {
//    // создаем ссылку на wsdl описание
//    if (args.length >= 3) {
//     throw new IllegalArgumentException("Wrong arguments quantity");
//    }
//            
////        System.out.println(src.getHelloString("JavaRush"));
////        System.out.println(connection().getxmlFile(args[0],args[1]));
////        System.out.println(src.getName(args[0],args[1]));
//    }
    
    public static WebService1 connection() throws MalformedURLException {
        // создаем ссылку на wsdl описание
        URL url =new URL("http://IP:8080/Hello_WS/WebServiceImplService?wsdl");

        // Параметры следующего конструктора смотрим в самом первом теге WSDL описания - definitions
        // 1-ый аргумент смотрим в атрибуте targetNamespace
        // 2-ой аргумент смотрим в атрибуте name
        QName qname =new QName("http://gpb/","WebServiceImplService");

        // Теперь мы можем дотянуться до тега service в wsdl описании,
        Service service = Service.create(url, qname);
        // а далее и до вложенного в него тега port, чтобы
        // получить ссылку на удаленный от нас объект веб-сервиса
        WebService1 src = service.getPort(WebService1.class);
        
        return src;
    }
    
}