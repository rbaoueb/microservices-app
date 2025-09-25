package com.example.customerservice.infrastructure.util;

import java.io.*;

public class SerializationUtils {

    public static byte[] toBytes(Object obj) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(obj);
            return bos.toByteArray();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromBytes(byte[] bytes, Class<T> clazz) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInputStream in = new ObjectInputStream(bis)) {
            Object obj = in.readObject();
            return (T) obj;
        }
    }
}
