package backend.sumnail.global.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.awt.geom.Point2D;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Base64;


@Converter(autoApply = true)
public class PointConverter implements AttributeConverter<Point2D, String> {

    @Override
    public String convertToDatabaseColumn(Point2D point) {
        if (point == null) {
            return null;
        }
        return "0x" + toHex(getWkb(point));
    }

    @Override
    public Point2D convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }

        try {
            // '0x' 제거 및 좌표값 추출
            String hexString = dbData.replace("0x", "").trim();

            byte[] bytes = hexToBytes(hexString);
            ByteBuffer buffer = ByteBuffer.wrap(bytes);
            buffer.order(ByteOrder.LITTLE_ENDIAN); // MySQL uses Little Endian

            int srid = buffer.getInt();
            float x = buffer.getFloat();
            float y = buffer.getFloat();

            return new Point2D.Float(x, y);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private byte[] getWkb(Point2D point) {
        ByteBuffer buffer = ByteBuffer.allocate(21); // 1 (byte) + 4 (int) + 8 (double) + 8 (double)
        buffer.order(ByteOrder.LITTLE_ENDIAN); // MySQL uses Little Endian

        buffer.put((byte) 0x01); // WKB format for Point
        buffer.putInt(0); // SRID - You may adjust this according to your requirements
        buffer.putDouble(point.getX());
        buffer.putDouble(point.getY());

        return buffer.array();
    }

    private String toHex(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    private byte[] hexToBytes(String hexString) {
        if (hexString == null || hexString.isEmpty()) {
            return new byte[0];
        }

        int len = hexString.length();
        byte[] data = new byte[len / 2];

        for (int i = 0; i < len; i += 2) {
            if (i + 1 < len) {
                String hexPair = hexString.substring(i, i + 2);
                data[i / 2] = (byte) Integer.parseInt(hexPair, 16);
            }
        }
        return data;
    }
}
