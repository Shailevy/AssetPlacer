package com.mgroup.assetsplacer;


/**
 * Created by Shai on 24-Oct-17.
 */

import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class Unzipper {

    private static final int BUFFER_SIZE = 8192;
    private static String TAG = Unzipper.class.getName();

    public static boolean unzip(InputStream sourceFile, String destinationFolder) {
        ZipInputStream zipInputStream = null;

        try {
            zipInputStream = new ZipInputStream(sourceFile);
            ZipEntry zipEntry;
            int count;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                String fileName = zipEntry.getName();
                fileName = fileName.substring(fileName.indexOf("/") + 1);
                File file = new File(destinationFolder, fileName);
                File dir = zipEntry.isDirectory() ? file : file.getParentFile();

                if (!dir.isDirectory() && !dir.mkdirs())
                    throw new FileNotFoundException("Invalid path: " + dir.getAbsolutePath());
                if (zipEntry.isDirectory()) continue;
                FileOutputStream outputStream = new FileOutputStream(file);
                try {
                    while ((count = zipInputStream.read(buffer)) != -1)
                        outputStream.write(buffer, 0, count);
                } finally {
                    Log.i(TAG, "Unzipped to: " + destinationFolder);
                    outputStream.close();
                }

            }
        } catch (IOException exception) {
            Log.d(TAG, exception.getMessage());
            return false;
        } finally {
            if (zipInputStream != null)
                try {
                    zipInputStream.close();
                } catch (IOException e) {
                    Log.d(TAG, e.getMessage());
                }
        }
        return true;
    }
}
