package backend.sumnail.global.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKBReader;
import org.locationtech.jts.io.WKBWriter;


@Converter(autoApply = true)
public class PointConverter implements AttributeConverter<Geometry, byte[]> {

    private final WKBReader wkbReader = new WKBReader();
    private final WKBWriter wkbWriter = new WKBWriter();

    @Override
    public byte[] convertToDatabaseColumn(Geometry geometry) {
        if (geometry == null) {
            return null;
        }
        return wkbWriter.write(geometry);
    }

    @Override
    public Geometry convertToEntityAttribute(byte[] wkb) {
        if (wkb == null) {
            return null;
        }
        try {
            return wkbReader.read(wkb);
        } catch (ParseException e) {

            return null;
        }
    }
}