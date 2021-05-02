/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

/**
 *
 * @author cristina
 */
import java.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author cristina
 */
public class AdaptadorLocalDate extends XmlAdapter<String, LocalDate> {
    
    //Estos métodos ayudan a darle formato a la fecha para que aparezca correctamente en el xml(para que no salga vacío)

    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v);
    }

    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }

}
