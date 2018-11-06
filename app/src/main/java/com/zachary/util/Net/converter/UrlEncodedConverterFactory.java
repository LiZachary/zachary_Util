/*
 * Copyright (c) &amp;#36;today.year, House365. All rights reserved.
 */

package com.zachary.util.Net.converter;

import android.util.Log;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.ByteString;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by Zachary on 2018/10/15.
 */
public class UrlEncodedConverterFactory extends Converter.Factory {
    private static final String TAG = "UrlEncodedConverter";

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/x-www-form-urlencoded");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    /**
     * Create an instance using a default instance for conversion. Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
    public static UrlEncodedConverterFactory create() {
        return new UrlEncodedConverterFactory();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        return null;
    }


    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new UrlEncodedRequestBodyConverter<>();
    }


    final class UrlEncodedRequestBodyConverter<T> implements Converter<T, RequestBody> {

        UrlEncodedRequestBodyConverter() {
        }

        @Override
        public RequestBody convert(T param) throws IOException {
            Log.d(TAG, "convert() called with: " + "param = [" + param + "]");
            Class<?> valueClass = param.getClass();
            if (isAssignableToOrFrom(valueClass, Map.class)) {
                throw new UnsupportedOperationException("please use @FormUrlEncoded and @FieldMap");
            }
            if (valueClass.isPrimitive() || isAssignableToOrFrom(valueClass, Collection.class)) {
                throw new UnsupportedOperationException("Only supports Simple Object");
            }
            Buffer buffer = new Buffer();
            Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
            try {
                Field[] fields = valueClass.getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    field.setAccessible(true);
                    Object o = field.get(param);
                    if (o == null) {
                        continue;
                    }
                    String value = String.valueOf(o);
                    String formName = URLEncoder.encode(field.getName(), "UTF-8");
                    String formValue = URLEncoder.encode(value, "UTF-8");
                    writer.write(formName + "=" + formValue);
                    if (i < fields.length - 1)
                        writer.write("&");
                }
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            } finally {
                writer.flush();
                buffer.flush();
                writer.close();
                buffer.close();
            }
            ByteString content = buffer.readByteString();
            return RequestBody.create(MEDIA_TYPE, content);
        }
    }

    public static boolean isAssignableToOrFrom(Class<?> classToCheck, Class<?> anotherClass) {
        return classToCheck.isAssignableFrom(anotherClass)
                || anotherClass.isAssignableFrom(classToCheck);
    }

    public interface UrlEncodedForm {

    }
}
