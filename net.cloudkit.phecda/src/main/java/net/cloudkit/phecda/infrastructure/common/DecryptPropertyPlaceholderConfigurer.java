//package net.cloudkit.phecda.infrastructure;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.Properties;
//
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
//import org.springframework.core.io.Resource;
//
//public class DecryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
//    private Resource[] locations;
//
//    private DecryptPropertiesPersister propertiesPersister = new DecryptPropertiesPersister();
//
//    private String fileEncoding = "utf-8";
//
//    private boolean ignoreResourceNotFound = false;
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void setLocations(Resource[] locations) {
//        this.locations = locations;
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void setFileEncoding(String encoding) {
//        this.fileEncoding = encoding;
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void setIgnoreResourceNotFound(boolean ignoreResourceNotFound) {
//        this.ignoreResourceNotFound = ignoreResourceNotFound;
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void loadProperties(Properties props) throws IOException {
//
//        // 属性文件是否为空
//        if (this.locations != null) {
//            // 循环读取属性文件
//            for (int i = 0; i < this.locations.length; i++) {
//                Resource location = this.locations[i];
//
//                InputStream is = null;
//                FileOutputStream fos = null;
//                try {
//
//                    is = location.getInputStream();
//
//                    // 检查文件是否是XML文件
//                    if (location.getFilename().endsWith(XML_FILE_EXTENSION)) {
//                        this.propertiesPersister.loadFromXml(props, is);
//                    }
//                    // 属性文件
//                    else {
//                        this.propertiesPersister.doLoad(props, new InputStreamReader(is, this.fileEncoding));
//                        String content = this.propertiesPersister.getEncryptContent();
//
//                        // 查找是否存在加密标识
//                        if (StringUtils.contains(content, DECRYPT_FLAG)) {
//                            try {
//                                File file = location.getFile();
//
//                                fos = new FileOutputStream(file);
//
//                                fos.write(this.propertiesPersister.getEncryptContent().getBytes());
//                                fos.flush();
//
//                            }
//                            finally {
//                                if (null != fos)
//                                {
//                                    fos.close();
//                                }
//                            }
//                        }
//                    }
//                } catch (IOException ex) {
//                    if (this.ignoreResourceNotFound) {
//                        if (logger.isWarnEnabled())
//                        {
//                            logger.warn("Could not load properties from " + location + ": " + ex.getMessage());
//                        }
//                    } else {
//                        throw ex;
//                    }
//                } finally {
//                    if (is != null) {
//                        is.close();
//                    }
//                }
//            }
//        }
//    }
//}
