package com.thoughtworks.spring.jpa.tomcat.services.impl;


import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.PutPolicy;
import com.thoughtworks.spring.jpa.tomcat.dao.PictureDao;
import com.thoughtworks.spring.jpa.tomcat.entities.Picture;
import com.thoughtworks.spring.jpa.tomcat.services.FileUploadService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService{
    public static final String ACCESS_KEY = "1-onUUcfnmC8QBZPI9A5lUDmkOkUkJw-GYbUhAXK";
    public static final String SECRET_KEY = "Y2aVet7I0r0e4lQJpo_B7w9lJ4iOoxPSv6MsfLR0";
    public static final String BUCKET_NAME = "dododo";
    @Autowired
    PictureDao pictureDao;


    private Picture pictureEntity = new Picture();

    private Date date = new Date();

    @Override
    public String uploadFile(InputStream inputStream) {
        PutExtra extra = new PutExtra();
        String key = UUID.randomUUID().toString();
        String uptoken = getToken();
        if (uptoken!=null) {
            PutRet ret = IoApi.Put(uptoken, key, inputStream, extra);
            if (ret.ok()) {
                return ret.getKey();
            }
        }
        return null;
    }

    @Override
    public void insertPicInfoToDb(String picKey, Long userId) {
        pictureEntity.setId(picKey);
        pictureEntity.setUser_id(userId);
        pictureEntity.setCreateDate(new Timestamp(date.getTime()));
        pictureEntity.setModifyDate(new Timestamp(date.getTime()));
        pictureDao.insertPicInfo(pictureEntity);
    }

    private String getToken(){
        Config.ACCESS_KEY = ACCESS_KEY;
        Config.SECRET_KEY = SECRET_KEY;
        Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
        String bucketName = BUCKET_NAME;
        PutPolicy putPolicy = new PutPolicy(bucketName);
        try {
            return putPolicy.token(mac);
        }catch (AuthException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
