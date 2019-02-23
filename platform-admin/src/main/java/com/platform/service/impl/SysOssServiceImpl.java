package com.platform.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.platform.dao.SysOssDao;
import com.platform.entity.SysOssEntity;
import com.platform.service.SysOssService;

import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service("sysOssService")
public class SysOssServiceImpl implements SysOssService {
    @Autowired
    private SysOssDao sysOssDao;

    @Override
    public SysOssEntity queryObject(Long id) {
        return sysOssDao.queryObject(id);
    }

    @Override
    public List<SysOssEntity> queryList(Map<String, Object> map) {
        return sysOssDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysOssDao.queryTotal(map);
    }

    @Override
    public void save(SysOssEntity sysOss) {
        sysOssDao.save(sysOss);
    }

    @Override
    public void update(SysOssEntity sysOss) {
        sysOssDao.update(sysOss);
    }

    @Override
    public void delete(Long id) {
        sysOssDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        sysOssDao.deleteBatch(ids);
    }

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SimpleMailMessage simpleMailMessage;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    
    /**
     * 
     * @Description: 从模板中构建邮件内容
     * @param nickName        用户昵称
     * @param content        邮件内容
     * @param email            接受邮件
     * 
     * @author leechenxiang
     * @date 2017年2月5日 下午1:22:00
     */
    public void send(Map<String,Object> map, String email,String subject) {
        String to = email;
        String text = "";
        try {
            // 根据模板内容，动态把map中的数据填充进去，生成HTML
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate("mail.ftl");
            // map中的key，对应模板中的${key}表达式
            text = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        sendMail(to, subject, text);
    }
    
    /**
     * 
     * @Description: 执行发送邮件
     * @param to            收件人邮箱
     * @param subject        邮件主题
     * @param content        邮件内容
     * 
     * @author leechenxiang
     * @date 2017年2月5日 下午1:22:34
     */
    public void sendMail(String to, String subject, String content) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            messageHelper.setFrom(simpleMailMessage.getFrom());
            if (subject != null) {
                messageHelper.setSubject(subject);
            } else {
                messageHelper.setSubject(simpleMailMessage.getSubject());
            }
            messageHelper.setTo(to);
            messageHelper.setText(content, true);
           javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
