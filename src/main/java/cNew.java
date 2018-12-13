import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class cNew
        extends URLClassLoader {

    private Class<?> classC = null;
    private Method methodA = null;
    private Method methodAByte = null;

    public cNew(ClassLoader paramClassLoader, File paramFile) throws MalformedURLException, NoSuchMethodException, ClassNotFoundException {
        super(new URL[]{paramFile.toURL()}, paramClassLoader);
        classC = ClassLoader.getSystemClassLoader().loadClass(c.class.getName());
        methodA = classC.getMethod("a", String.class);
        methodAByte = classC.getDeclaredMethod("a", byte[].class);
        methodAByte.setAccessible(true);
        if (paramClassLoader == null) {
            throw new RuntimeException();
        }
    }


    public Class loadClass(String paramString, boolean paramBoolean)
            throws ClassNotFoundException {
        Object localObject = null;
        localObject = findLoadedClass(paramString);
        if (localObject == null) {
            Class localClass = null;
            try {
                localClass = getParent().loadClass(paramString);
                if (localClass.getClassLoader() != getParent()) {
                    localObject = localClass;
                }
            } catch (ClassNotFoundException localClassNotFoundException1) {
            } catch (ClassFormatError localClassFormatError) {
            }
            if (localObject == null) {
                try {
                    localObject = findClass(paramString);
                } catch (ClassNotFoundException localClassNotFoundException2) {
                    localObject = localClass;
                }
            }
        }
        if (localObject == null) {
            throw new ClassNotFoundException(paramString);
        }
        if (paramBoolean) {
            resolveClass((Class) localObject);
        }
        return (Class) localObject;
    }

    protected Class findClass(String paramString)
            throws ClassNotFoundException {
        String str = paramString.replace('.', '/') + ".class";
        URL localURL = getResource(str);
        Class localClass = null;
        if (localURL == null) {
            throw new ClassNotFoundException(paramString);
        }
        InputStream localInputStream = null;
        try {
            for (int i = 0; i <= 999999; i++) {
                try {
                    System.out.println("i: " + i);
                    String pin = String.format("%06d", i);
                    System.out.println("pin:" + pin);
                    localInputStream = localURL.openStream();
                    byte[] arrayOfByte = a(localInputStream);
                    if ((a("QUM=").equals(paramString)) || (a("QUMkMw==").equals(paramString)) || (a("Uw==").equals(paramString))) {
                        a(arrayOfByte, pin.getBytes());
                    }
                    localClass = defineClass(paramString, arrayOfByte, 0, arrayOfByte.length);

                    System.out.println("pin to decrypt: " + pin);
                    return localClass;
                } catch (ClassFormatError e) {
                }
            }
        } catch (IOException | InvocationTargetException | IllegalAccessException e) {
            System.out.println(e);
        } finally {
            if (localInputStream != null) {
                try {
                    localInputStream.close();
                } catch (Exception localException2) {
                    System.out.println(localException2);
                }
            }
        }

        return localClass;
    }

    private static void a(byte[] paramArrayOfByte, byte[] pin) {
        byte[] arrayOfByte = pin;
        int i = 8;
        int j = 0;
        while (i < paramArrayOfByte.length) {
            if (j > 5) {
                j = 0;
            }
            paramArrayOfByte[i] = ((byte) (paramArrayOfByte[i] ^ arrayOfByte[j]));
            j++;
            i++;
        }
    }


    public String a(String paramString) throws InvocationTargetException, IllegalAccessException {
        return (String) methodA.invoke(null, paramString);
    }

    private static byte[] a(InputStream paramInputStream)
            throws IOException {
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
        byte[] arrayOfByte = new byte['?'];
        int i;
        while ((i = paramInputStream.read(arrayOfByte)) > 0) {
            localByteArrayOutputStream.write(arrayOfByte, 0, i);
        }
        return localByteArrayOutputStream.toByteArray();
    }


}
