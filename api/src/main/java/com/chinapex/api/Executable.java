package com.chinapex.api;

import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by ning on 16-8-1.
 */
public class Executable {
    public static void main(String[] args) throws Exception {
        Executable executable = new Executable();
        executable.launchGrizzly(args);
    }

    private void launchGrizzly(String[] args) throws Exception {
        URL warUrl = getWarUrl();
        invokeLauncher(loadRequiredClassLoaderWithJars(warUrl), args, warUrl);
    }

    private URL getWarUrl() {
        ProtectionDomain protectionDomain = Executable.class.getProtectionDomain();
        return protectionDomain.getCodeSource().getLocation();
    }

    private void invokeLauncher(ClassLoader urlClassLoader, String[] args, URL warUrl) {
        try {
            Class launcher = urlClassLoader.loadClass("com.chinapex.api.Launcher");
            Method mainMethod = launcher.getMethod("start", new Class[]{String.class, String.class});
            String path = warUrl.getPath();
            mainMethod.invoke(null, path.substring(0, path.lastIndexOf("/")) + File.separator + "tmp", "1947");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private ClassLoader loadRequiredClassLoaderWithJars(URL warUrl) throws Exception {
        List jarUrls = extractJarsFromWar(warUrl.getPath());
        ClassLoader urlClassLoader = new URLClassLoader((URL[]) jarUrls.toArray(new URL[jarUrls.size()]));
        Thread.currentThread().setContextClassLoader(urlClassLoader);
        return urlClassLoader;
    }

    private List extractJarsFromWar(String warPath) throws Exception {
        JarFile jarFile = new JarFile(warPath);
        System.out.println("======load jars======");
        List jarUrls = new ArrayList();
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            if (!jarEntry.getName().endsWith(".jar")) continue;
            URL tmpJar = createTmpJar(jarFile, jarEntry);
            System.out.println("load " + jarEntry.getName() + " at " + tmpJar.toString());
            jarUrls.add(tmpJar);
        }
        return jarUrls;
    }

    private URL createTmpJar(JarFile jarFile, JarEntry jarEntry) throws IOException {
        File tmpFile = createTmpFile(jarEntry.getName());
        writeToTmpJar(jarFile, jarEntry, tmpFile);
        return tmpFile.toURI().toURL();
    }

    private void writeToTmpJar(JarFile jarFile, JarEntry jarEntry, File tmpFile) throws IOException {
        InputStream inStream = null;
        OutputStream outStream = null;
        try {
            inStream = jarFile.getInputStream(jarEntry);
            outStream = new FileOutputStream(tmpFile);
            writeStream(inStream, outStream);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outStream != null) {
                outStream.close();
            }
            if (inStream != null) {
                inStream.close();
            }
        }
    }

    private void writeStream(InputStream inStream, OutputStream outStream) throws IOException {
        byte[] buffer = new byte[8192];
        int readLength;
        while ((readLength = inStream.read(buffer)) > 0)
            outStream.write(buffer, 0, readLength);
    }

    private File createTmpFile(String entryPath) throws IOException {

        File tmpFile;
        try {
            tmpFile = File.createTempFile(entryPath.replaceAll("/", "_"), "api");
        } catch (IOException e) {
            String tmpdir = System.getProperty("java.io.tmpdir");
            throw new IOException("Failed to extract " + entryPath + " to " + tmpdir, e);
        }
        tmpFile.deleteOnExit();
        return tmpFile;
    }
}
