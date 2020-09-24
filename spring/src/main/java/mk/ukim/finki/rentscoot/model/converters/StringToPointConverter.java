package mk.ukim.finki.rentscoot.model.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

@Component
public class StringToPointConverter implements Converter<String, Point> {
    @Override
    public Point convert(String s) {
        if(s.length()>1){
            String[] mapCoordinates = s.split(",");
            double x = Double.parseDouble(mapCoordinates[0]);
            double y = Double.parseDouble(mapCoordinates[1]);
            return new Point(x,y);
        }
        else return null;
    }
}
